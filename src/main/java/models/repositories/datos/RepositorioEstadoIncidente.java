package models.repositories.datos;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import server.Server;
import server.utils.ICrudViewsHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEstadoIncidente implements WithSimplePersistenceUnit,ICrudRepository {

    EntityManager entityManager = Server.createEntityManager();
    @Override
    public List buscarTodos() {
        return null;
    }

    @Override
    public Object buscar(Long id) {
        return null;
    }

    @Override
    public void guardar(Object o) {

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(o);
        tx.commit();
    }

    @Override
    public void actualizar(Object o) {
        withTransaction(() -> { entityManager.merge(o);});
    }

    @Override
    public void eliminar(Object o) {

    }
}
