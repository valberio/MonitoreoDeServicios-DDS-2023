package domain.entidades;


import domain.Persistente;
import domain.services.georef.entities.Ubicacion;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Table(name="establecimiento")
public class Establecimiento extends Persistente {
    @Column(name="nombre")
    private String nombre;
    @Embedded
    private Ubicacion ubicacionGeografica;
    @OneToMany
    private List<PrestacionDeServicio> serviciosBrindados;
    @ManyToOne
    private Entidad entidad;

    public Establecimiento()
    {
        serviciosBrindados=new ArrayList<>();
    }

    public void brindarServicios(PrestacionDeServicio ... servicio) {

        serviciosBrindados.addAll(List.of(servicio));
    }

    public void quitarPrestacionDeServicio(PrestacionDeServicio servicio) {

        serviciosBrindados.remove(servicio);
    }

    public Stream<Servicio> obtenerServiciosIncidentados() {

        return serviciosBrindados.stream().filter(prestacion->!prestacion.getEstaHabilitado()).map(prestacion->prestacion.getServicio());

    }
}
