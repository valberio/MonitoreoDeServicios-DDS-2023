package domain.incidentes;

import datos.ArchivoIncidentes;
import domain.servicios.PrestacionDeServicio;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class IncidentesSemanales {

    public ArrayList<Incidente> obtenerse(){
        ArchivoIncidentes archivoIncidentes = new ArchivoIncidentes();

        return filtrarOcurridosUltimaSemana(ArchivoIncidentes.getInstance().incidentes);
    }

    public ArrayList<Incidente> filtrarOcurridosUltimaSemana(ArrayList<Incidente> incidentes){
         LocalDateTime inicioUltimaSemana = LocalDateTime.now().minusWeeks(1);

        return (ArrayList<Incidente>) incidentes.stream().filter(incidente -> incidente.fechaReporte.isAfter(inicioUltimaSemana));
    }


}






