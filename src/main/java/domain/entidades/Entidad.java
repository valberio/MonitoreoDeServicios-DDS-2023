package domain.entidades;

import domain.Localizacion.Localizacion;
import domain.services.georef.entities.Provincia;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

@Getter
@Setter
public class Entidad {
    private String nombre;

    private Integer cantidadReportes;
    private Double promedioCierre;
    private ArrayList<Establecimiento> establecimientosAsociados = new ArrayList<>();

    public Entidad(String nombre) {
        this.nombre = nombre;
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
    }

    public Stream<Servicio> serviciosConIncidente() {
        return establecimientosAsociados.stream().flatMap(Establecimiento::obtenerServiciosIncidentados);
    }

    public ArrayList<Localizacion> dondeOpera() {
        return establecimientosAsociados.stream().map(establecimiento -> establecimiento.getUbicacionGeografica()).collect(Collectors.toCollection(ArrayList::new));
    }

}




