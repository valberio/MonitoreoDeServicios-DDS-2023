package domain.incidentes.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.opencsv.CSVWriter;

import java.util.*;
import java.util.stream.Collectors;


public class Ranking implements Job {

        Filtrador filtradorDeIncidentes = new Filtrador();
        ArrayList<Incidente> incidentesSemanales = filtradorDeIncidentes.filtrarIncidentesUltimaSemana();


        public ArrayList<Entidad> generarPorPromedioDeCierre(){
                Map<Entidad, List<Incidente>> incidentesPorEntidad = filtradorDeIncidentes.separarPorEntidadAfectada(incidentesSemanales);
                Map<Entidad, Integer> promedioCierrePorEntidad = this.generarPromedioCierrePorEntidad(incidentesPorEntidad);

                ArrayList<Entidad> ranking = new ArrayList<>(promedioCierrePorEntidad.keySet());
                ranking.sort(new RepeticionEntidadesComparador(promedioCierrePorEntidad));

                return ranking;
        }

        public ArrayList<Entidad> generarPorCantidadDeReportes(){
                Map<Entidad, Integer> conteoEntidades = new HashMap<>();
                ArrayList<Entidad> entidades = filtradorDeIncidentes.filtrarRepetidosEn24hs(incidentesSemanales).stream().map(incidente->incidente.obtenerEntidad()).collect(Collectors.toCollection(ArrayList::new));
                for(Entidad entidad : entidades){
                        conteoEntidades.put(entidad, conteoEntidades.getOrDefault(entidad, 0) + 1);
                }

                ArrayList<Entidad> ranking = new ArrayList<>(new HashSet<>(entidades));
                ranking.sort(new RepeticionEntidadesComparador(conteoEntidades));

                return ranking;

        }


        public Map<Entidad, Integer> generarPromedioCierrePorEntidad(Map<Entidad, List<Incidente>> incidentesPorEntidad) {
                Map<Entidad, Integer> promedioCierrePorEntidad = new HashMap<>();
                for (Map.Entry<Entidad, List<Incidente>> entry : incidentesPorEntidad.entrySet()) {
                        Entidad entidad = entry.getKey();
                        List<Incidente> incidentes = entry.getValue();
                        long sumaTiempoDeCierre = incidentes.stream()
                                .mapToLong(Incidente::obtenerTiempoDeCierre)
                                .sum();
                        int cantIncidentes = incidentes.size();
                        promedioCierrePorEntidad.put(entidad, (int) (sumaTiempoDeCierre / cantIncidentes));
                }
                return promedioCierrePorEntidad;
        }

        public void execute(JobExecutionContext context) throws JobExecutionException {
                ArrayList<Entidad> rankingPorCantidadDeReportes = this.generarPorCantidadDeReportes();
                ArrayList<Entidad> rankingPorPromedioDeCierre =this.generarPorPromedioDeCierre();

                String rutaArchivoRankings = "main/java/datos/rankings.csv";

                try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivoRankings))) {
                        // Escribir los datos en el archivo CSV

                        writer.writeNext(Entidad.obtenerEncabezadosCSV());

                        for (Entidad entidad : rankingPorCantidadDeReportes) {
                                writer.writeNext(entidad.obtenerDatosCSV());
                        }

                        writer.writeNext(Entidad.obtenerEncabezadosCSV());
                        for (Entidad entidad : rankingPorPromedioDeCierre) {
                                writer.writeNext(entidad.obtenerDatosCSV());
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


}
