package domain.incidentes.rankings;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void execute(JobExecutionContext context) {

        this.generarArchivoPorCantidadDeReportes();
        this.generarArchivoPorPromedioCierre();


    }
    private void generarArchivoPorCantidadDeReportes() {

        ArrayList<Entidad> rankingPorCantidadDeReportes = this.generarPorCantidadDeReportes();

        String rutaArchivoRankings = "main/java/datos/rankingReportes.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivoRankings))) {

            List<String[]> existingData = this.reescribirDatosPreviosRanking(rutaArchivoRankings);

            String[] weekDates = this.obtenerFechasSemanaActual();

            writer.writeNext(new String[]{"Semana", weekDates[0] + " a " + weekDates[1]});


            writer.writeNext(Entidad.obtenerEncabezadosCSV("INFORME_POR_CANTIDAD_INCIDENTES"));

            for (Entidad entidad : rankingPorCantidadDeReportes) {
                writer.writeNext(entidad.obtenerDatosCSV("INFORME_POR_CANTIDAD_INCIDENTES"));
            }

            writer.writeAll(existingData);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String[]> reescribirDatosPreviosRanking(String rutaArchivoRankings) {

        List<String[]> existingData = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivoRankings))) {
            existingData = reader.readAll();
        } catch (IOException | CsvException e) {
            // El archivo no existe o no se pudo leer, lo dejamos vacío
        }

        return existingData;

    }

    private String[] obtenerFechasSemanaActual() {
        // Obtener la fecha de inicio y fin de la semana actual (de lunes a domingo)
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate fechaInicioSemana = LocalDate.now().with(java.time.DayOfWeek.MONDAY); // Lunes actual
        String fechaInicioSemanaStr = fechaInicioSemana.format(dateFormat);

        LocalDate fechaFinSemana = fechaInicioSemana.plusDays(6); // Domingo actual
        String fechaFinSemanaStr = fechaFinSemana.format(dateFormat);

        return new String[]{fechaInicioSemanaStr, fechaFinSemanaStr};
    }
    private void generarArchivoPorPromedioCierre(){

        ArrayList<Entidad> rankingPorPromedioDeCierre = this.generarPorPromedioDeCierre();

        String rutaArchivoRankings = "main/java/datos/rankingPromedioCierre.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivoRankings))) {

            List<String[]> existingData = this.reescribirDatosPreviosRanking(rutaArchivoRankings);

            String[] weekDates = this.obtenerFechasSemanaActual();

            writer.writeNext(new String[]{"Semana", weekDates[0] + " a " + weekDates[1]});

            writer.writeNext(Entidad.obtenerEncabezadosCSV("INFORME_POR_PROMEDIO_CIERRE"));

            for (Entidad entidad : rankingPorPromedioDeCierre) {
                writer.writeNext(entidad.obtenerDatosCSV("INFORME_POR_PROMEDIO_CIERRE"));
            }

            writer.writeAll(existingData);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
