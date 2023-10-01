package models.repositories.datos;

import models.entities.domain.entidades.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;

public class RepositorioEstablecimientos implements WithSimplePersistenceUnit {

    public void agregarEstablecimiento(Establecimiento establecimiento) {
       EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(establecimiento);
        tx.commit();
    }

    public void eliminarEstablecimiento(Establecimiento establecimiento) {
        entityManager().remove(establecimiento);
    }

}
