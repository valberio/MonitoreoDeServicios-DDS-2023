package domain.incidentes;

import domain.entidades.Entidad;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class Ranking {

        IncidentesSemanales incidentesSemanales = new IncidentesSemanales();

        public ArrayList<Entidad> generarPorPromedioDeCierre(){
                Map<Entidad, List<Incidente>> incidentesPorEntidad = incidentesSemanales.obtenerse().stream().collect((Collectors.groupingBy(Incidente::obtenerEntidad)));
                Map<Entidad, Integer> promedioCierrePorEntidad = this.generarPromedioCierrePorEntidad(incidentesPorEntidad);

                List<Entidad> ranking = new ArrayList<>(promedioCierrePorEntidad.keySet());
                ranking.sort(new ComparadorEntidadesPromedio(promedioCierrePorEntidad));

                return (ArrayList<Entidad>) ranking;
        }

        public ArrayList<Entidad> generarPorCantidadDeReportes(){
                Map<Entidad, Integer> conteoEntidades = new HashMap<>();
                List<Entidad> entidades = (List<Entidad>) filtrarRepetidosEn24hs(incidentesSemanales.obtenerse()).stream().map(incidente -> incidente.getServicioAfectado().obtenerEntidadAfectada());
                for(Entidad entidad : entidades){
                        conteoEntidades.put(entidad, conteoEntidades.getOrDefault(entidad, 0) + 1);
                }

                List<Entidad> ranking = new ArrayList<>(new HashSet<>(entidades));
                ranking.sort(new RepeticionEntidadesComparador(conteoEntidades));

                return (ArrayList<Entidad>) ranking;

        }


        public ArrayList<Incidente> filtrarRepetidosEn24hs(ArrayList<Incidente> incidentes){
                //TODO
                return null;
        }

        public ArrayList<ArrayList<Incidente>> obtenerIncidentesPorPrestacion(ArrayList<Incidente> incidentes){
                ArrayList<ArrayList<Incidente>> incidentesPorPrestacion = new ArrayList<>();


                for (Incidente incidente : incidentes) {
                        boolean encontrada = false;

                        // Buscar la sublista correspondiente a la prestación del incidente
                        for (ArrayList<Incidente> prestacion : incidentesPorPrestacion) {
                                if (prestacion.get(0).getServicioAfectado().equals(incidente.getServicioAfectado())) {
                                        // Agregar el incidente a la sublista existente
                                        prestacion.add(incidente);
                                        encontrada = true;
                                        break;
                                }
                        }

                        // Si no se encontró una sublista existente, crear una nueva y agregar el incidente
                        if (!encontrada) {
                                ArrayList<Incidente> nuevaPrestacion = new ArrayList<>();
                                nuevaPrestacion.add(incidente);
                                incidentesPorPrestacion.add(nuevaPrestacion);
                        }
                }

                return incidentesPorPrestacion;
        }

        public Long obtenerDiferenciaDeRegistroEntre(Incidente incidente1, Incidente incidente2){
                Duration duracion = Duration.between(incidente1.getFechaReporte(), incidente2.getFechaReporte());
                return (duracion.toHours() % 24);

        }

        //if(obtenerDiferenciaDeRegistroEntre(incidente1, incidente2) < 24)
        //if(incidente1.getfechaDeReporte().before(incidente1.getfechaDeReporte())) me quedo con la 1 else me quedo con la 2
        //incidente.getEstado() == EstadoIncidente.ACTIVO; lo tengo que descartar. Si ya fue resuelto, si se considera para el ranking



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
}
