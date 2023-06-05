package domain.entidades;

import domain.Localizacion.Localizacion;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class Establecimiento {
    private String nombre;
    private Localizacion ubicacionGeografica;
    private ArrayList<PrestacionDeServicio> serviciosBrindados = new ArrayList<>();
    public Establecimiento(String nombre) {
        this.nombre = nombre;
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
