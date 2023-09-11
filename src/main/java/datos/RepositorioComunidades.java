package datos;

import domain.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioComunidades implements WithSimplePersistenceUnit {

    public void agregarComunidad(Comunidad comunidad) {

        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(comunidad);
        tx.commit();
    }

    public void eliminarComunidad(Comunidad comunidad) {
        entityManager().remove(comunidad);
    }

    public Comunidad obtenerComunidad(Long id) { //TODO
        return find(Comunidad.class, id);
    }
}
