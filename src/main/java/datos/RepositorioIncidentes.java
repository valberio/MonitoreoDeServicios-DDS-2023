package datos;



import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class RepositorioIncidentes {

    public ArrayList<Incidente> incidentes = new ArrayList<Incidente>();
    private static RepositorioIncidentes instancia = null;

    public static RepositorioIncidentes getInstance(){
        if (instancia == null){instancia = new RepositorioIncidentes();}
        return instancia;
    }
    public void guardarIncidente(Incidente incidente){
        this.incidentes.add(incidente);
    }

    public List<Incidente> consultarIncidentePorEstado(EstadoIncidente estado){
        return incidentes.stream().filter(incidente -> incidente.getEstado() == estado).toList();
    }
}
