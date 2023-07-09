package domain.incidentes.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Ranking implements Job {
    Filtrador filtradorDeIncidentes = new Filtrador();
    ArrayList<Incidente> incidentesSemanales = filtradorDeIncidentes.filtrarIncidentesUltimaSemana();

    public ArrayList<Entidad> generarPorPromedioDeCierre() {

        Map<Entidad, List<Incidente>> incidentesPorEntidad = filtradorDeIncidentes.separarPorEntidadAfectada(incidentesSemanales);
        Map<Entidad, Double> promedioCierrePorEntidad = this.generarPromedioCierrePorEntidad(incidentesPorEntidad);

        ArrayList<Entidad> ranking = new ArrayList<>(promedioCierrePorEntidad.keySet());
        ranking.sort(new RepeticionEntidadesComparador(promedioCierrePorEntidad));

        return ranking;
    }

    public ArrayList<Entidad> generarPorCantidadDeReportes() {

        Map<Entidad, Double> conteoEntidades = new HashMap<>();
        ArrayList<Entidad> entidades = filtradorDeIncidentes.filtrarRepetidosEn24hs(incidentesSemanales).stream().map(incidente -> incidente.obtenerEntidad()).collect(Collectors.toCollection(ArrayList::new));
        for (Entidad entidad : entidades) {
            conteoEntidades.put(entidad, conteoEntidades.getOrDefault(entidad, 0.0) + 1);

            if(conteoEntidades.get(entidad)!=0) {
                entidad.setCantidadReportes(conteoEntidades.get(entidad).intValue());
            }
        }

        ArrayList<Entidad> ranking = new ArrayList<>(new HashSet<>(entidades));
        ranking.sort(new RepeticionEntidadesComparador(conteoEntidades));

        return ranking;

    }


    public Map<Entidad, Double> generarPromedioCierrePorEntidad(Map<Entidad, List<Incidente>> incidentesPorEntidad) {

        Map<Entidad, Double> promedioCierrePorEntidad = new HashMap<>();
        for (Map.Entry<Entidad, List<Incidente>> entry : incidentesPorEntidad.entrySet()) {
            Entidad entidad = entry.getKey();
            List<Incidente> incidentes = entry.getValue();
            long sumaTiempoDeCierre = incidentes.stream()
                    .mapToLong(Incidente::obtenerTiempoDeCierre)
                    .sum();
            int cantIncidentes = incidentes.size();
            promedioCierrePorEntidad.put(entidad, (double) (sumaTiempoDeCierre / cantIncidentes));
            entidad.setPromedioCierre(promedioCierrePorEntidad.get(entidad));
        }
        return promedioCierrePorEntidad;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        this.generarArchivoPorCantidadDeReportes();
        this.generarArchivoPorPromedioCierre();


    }
    private void generarArchivoPorCantidadDeReportes() {

        ArrayList<Entidad> rankingPorCantidadDeReportes = this.generarPorCantidadDeReportes();

        String rutaArchivoRankings = "main/java/datos/rankingReportes.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivoRankings))) {

            writer.writeNext(Entidad.obtenerEncabezadosCSV("INFORME_POR_CANTIDAD_INCIDENTES"));

            for (Entidad entidad : rankingPorCantidadDeReportes) {
                writer.writeNext(entidad.obtenerDatosCSV("INFORME_POR_CANTIDAD_INCIDENTES"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void generarArchivoPorPromedioCierre(){

        ArrayList<Entidad> rankingPorPromedioDeCierre = this.generarPorPromedioDeCierre();

        String rutaArchivoRankings = "main/java/datos/rankingPromedioCierre.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivoRankings))) {

            writer.writeNext(Entidad.obtenerEncabezadosCSV("INFORME_POR_PROMEDIO_CIERRE"));

            for (Entidad entidad : rankingPorPromedioDeCierre) {
                writer.writeNext(entidad.obtenerDatosCSV("INFORME_POR_PROMEDIO_CIERRE"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
