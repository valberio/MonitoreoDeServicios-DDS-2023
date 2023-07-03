package domain.incidentes;

import domain.entidades.Entidad;

import java.util.Comparator;
import java.util.Map;

public class ComparadorEntidadesPromedio implements Comparator<Entidad> {

    private final Map<Entidad, Integer> promedioEntidades;

    public ComparadorEntidadesPromedio(Map<Entidad, Integer> conteoEntidades){
        this.promedioEntidades = conteoEntidades;
    }

    @Override
    public int compare(Entidad entidad1, Entidad entidad2){
        int rep1 = promedioEntidades.get(entidad1);
        int rep2 = promedioEntidades.get(entidad2);

        return Integer.compare(rep1, rep2);
    }
}
