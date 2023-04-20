package domain.transporte;

import domain.servicios.Servicio;
import java.util.ArrayList;

public class Estacion {
    private String nombre;
    private String ubicacionGeografica;
    private ArrayList<Servicio> serviciosBrindados;


    public void brindarUnServicio(Servicio servicio) {

        serviciosBrindados.add(servicio);
    }

    public void quitarUnServicio(Servicio servicio) {

        serviciosBrindados.remove(servicio);
    }


}
