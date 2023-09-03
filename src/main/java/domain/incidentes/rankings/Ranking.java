package domain.incidentes.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import org.quartz.JobExecutionContext;

import java.util.ArrayList;

public abstract class Ranking {

    Filtrador filtradorDeIncidentes = new Filtrador();
    ArrayList<Incidente> incidentesSemanales = filtradorDeIncidentes.filtrarIncidentesUltimaSemana();

    public abstract ArrayList<Entidad> generarRanking();
}
