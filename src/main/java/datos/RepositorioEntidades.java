package datos;

import domain.entidades.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEntidades implements WithSimplePersistenceUnit {

    public void agregarEntidad(Entidad entidad) {

            EntityTransaction tx = entityManager().getTransaction();
            tx.begin();
            entityManager().persist(entidad);
            tx.commit();
        }

        public void eliminarEntidad(Entidad entidad) {
            entityManager().remove(entidad);
        }

        public Entidad obtenerEntidad(Long id) {
            return find(Entidad.class, id);
        }
}


