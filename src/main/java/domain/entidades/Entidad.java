package domain.entidades;


import domain.Persistente;
import domain.services.georef.entities.Ubicacion;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="entidad")
public class Entidad extends Persistente {
    @Override
    public String toString() {
        return "Entidad nombre=" + nombre ;
    }
    @Column(name="nombre")
    private String nombre;
    @Column(name="cantidad_reportes")
    private Integer cantidadReportes;
    @Column(name = "promedio_de_cierre")
    private Double promedioCierre;
    @OneToMany(mappedBy="entidad")
    private List<Establecimiento> establecimientosAsociados;
    @ManyToOne
    @JoinColumn(name="prestadora_de_servicio_id", referencedColumnName = "id")
    private PrestadoraDeServicio prestadora;

    public Entidad() {
        establecimientosAsociados = new ArrayList<>();
    }

    public static String[] obtenerEncabezadosCSV(String opcion) {

        switch (opcion) {
            case "INFORME_POR_PROMEDIO_CIERRE":
                return new String[]{"Nombre", "Promedio de Cierre"};
            case "INFORME_POR_CANTIDAD_INCIDENTES":
                return new String[] {"Nombre","Cantidad de Reportes"};
            default:
                throw new RuntimeException("No es valida la opcion de archivo CSV");
        }
    }

    public String[] obtenerDatosCSV(String opcion) {

        switch(opcion) {
            case "INFORME_POR_PROMEDIO_CIERRE":
                return new String[]{"Nombre", String.valueOf(this.promedioCierre)};
            case "INFORME_POR_CANTIDAD_INCIDENTES":
                return new String[] {"Nombre",String.valueOf(this.cantidadReportes)};
            default:
                throw new RuntimeException("No es valida la opcion de archivo CSV");
        }
    }

    public void agregarEstablecimientos(Establecimiento... unEstablecimiento) {

        establecimientosAsociados.addAll(List.of(unEstablecimiento));

        for(Establecimiento establecimiento: unEstablecimiento) {

            establecimiento.setEntidad(this);
        }
    }



    public Stream<Servicio> serviciosConIncidente() {
        return establecimientosAsociados.stream().flatMap(Establecimiento::obtenerServiciosIncidentados);
    }

    public ArrayList<Ubicacion> dondeOpera() {
        return establecimientosAsociados.stream().map(establecimiento -> establecimiento.getUbicacionGeografica()).collect(Collectors.toCollection(ArrayList::new));
    }

}




