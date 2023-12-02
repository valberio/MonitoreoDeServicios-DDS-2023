package models.repositories.datos;

import models.entities.domain.entidades.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import server.Server;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEstablecimientos implements WithSimplePersistenceUnit,ICrudRepository {
    EntityManager entityManager = Server.createEntityManager();
    @Override
    public List buscarTodos() {
        return entityManager.createQuery("from " + Establecimiento.class.getName()).getResultList();
    }

    @Override
    public Object buscar(Long id) {
        return entityManager.find(Establecimiento.class, id);
    }

    @Override
    public void guardar(Object o) {

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(o);
        tx.commit();
    }

    @Override
    public void eliminar(Object o) {
        entityManager.remove(o);
    }

    @Override
    public void actualizar(Object o) {
        withTransaction(() -> { entityManager.merge(o);});
    }

}
