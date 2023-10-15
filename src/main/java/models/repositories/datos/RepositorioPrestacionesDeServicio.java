package models.repositories.datos;

import models.entities.domain.incidentes.Incidente;
import models.entities.domain.servicios.PrestacionDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioPrestacionesDeServicio implements WithSimplePersistenceUnit, ICrudRepository {

    @Override
    public List buscarTodos() {
        return entityManager().createQuery("from " + PrestacionDeServicio.class.getName()).getResultList();
    }

    @Override
    public Object buscar(Long id) {
        return entityManager().find(PrestacionDeServicio.class, id);
    }

    @Override
    public void guardar(Object o) {

        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(o);
        tx.commit();
    }

    @Override
    public void eliminar(Object o) {
        entityManager().remove(o);
    }

    @Override
    public void actualizar(Object o) {
        withTransaction(() -> { entityManager().merge(o);});
    }


    public Long obtenerIdDelServicioPorNombre(String nombrePrestacion) {
        return withTransaction(() -> {
            TypedQuery<PrestacionDeServicio> query = entityManager().createQuery(
                    "SELECT s FROM Servicio s WHERE s.nombre = :nombre", PrestacionDeServicio.class);
            query.setParameter("nombre", nombrePrestacion);

            PrestacionDeServicio servicio = query.getSingleResult();

            if (servicio != null) {
                return servicio.getId();
            } else {
                return null;
            }
        });
    }
}
