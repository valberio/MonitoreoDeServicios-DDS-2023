package domain.incidentes.rankings;

import domain.entidades.Entidad;

import java.util.Comparator;
import java.util.Map;

class RepeticionEntidadesComparador implements Comparator<Entidad> {
    private final Map<Entidad, Integer> conteoEntidades;

    public RepeticionEntidadesComparador(Map<Entidad, Integer> conteoEntidades){
        this.conteoEntidades = conteoEntidades;
    }

    @Override
    public int compare(Entidad entidad1, Entidad entidad2){
        int rep1 = conteoEntidades.get(entidad1);
        int rep2 = conteoEntidades.get(entidad2);

        return Integer.compare(rep1, rep2);
    }
}
