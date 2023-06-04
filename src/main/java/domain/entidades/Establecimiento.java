package domain.entidades;

import domain.Localizacion.Localizacion;
import domain.servicios.Servicio;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Establecimiento {
    private String nombre;
    private Localizacion ubicacionGeografica;
    private ArrayList<Servicio> serviciosBrindados = new ArrayList<>();


    public void brindarUnServicio(Servicio servicio) {

        serviciosBrindados.add(servicio);
    }

    public void quitarUnServicio(Servicio servicio) {

        serviciosBrindados.remove(servicio);
    }

    public Stream<Servicio> obtenerServiciosIncidentados() {

        return serviciosBrindados.stream().filter(servicio->!servicio.getEstaHabilitado());

    }

}
