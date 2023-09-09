package datos;

import domain.entidades.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class RepositorioEstablecimientos implements WithSimplePersistenceUnit {

    public void agregarEstablecimiento(Establecimiento establecimiento) {
        withTransaction( () -> {
            entityManager().persist(establecimiento); });
    }

}
