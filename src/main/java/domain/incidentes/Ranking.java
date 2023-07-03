package domain.incidentes;

import domain.entidades.Entidad;

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
