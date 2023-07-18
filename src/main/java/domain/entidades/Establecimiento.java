package domain.entidades;


import domain.services.georef.entities.Ubicacion;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
public class Establecimiento {

    private String nombre;
    private Ubicacion ubicacionGeografica;
    private ArrayList<PrestacionDeServicio> serviciosBrindados = new ArrayList<>();
    private Entidad entidad;

    public Establecimiento(String nombre, Ubicacion ubicacionGeografica)
    {
        this.nombre = nombre;
        this.ubicacionGeografica = ubicacionGeografica;
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
