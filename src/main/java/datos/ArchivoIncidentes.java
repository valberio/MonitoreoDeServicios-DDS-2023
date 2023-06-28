package datos;

import domain.incidente.Incidente;

import java.util.ArrayList;

public class ArchivoIncidentes {

    public ArrayList<Incidente> incidentes = new ArrayList<Incidente>();
    private static ArchivoIncidentes instancia = null;

    public static ArchivoIncidentes getInstance(){
        if (instancia == null){instancia = new ArchivoIncidentes();}
        return instancia;
    }
    public void guardarIncidente(Incidente incidente){
        this.incidentes.add(incidente);
    }
}
