package datos;



import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

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

    public List<Incidente> consultarIncidentePorEstado(EstadoIncidente estado){
        return incidentes.stream().filter(incidente -> incidente.getEstado() == estado).toList();
    }
}
