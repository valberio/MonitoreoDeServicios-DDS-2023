package models.repositories.datos;

import models.entities.domain.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioComunidades implements WithSimplePersistenceUnit, ICrudRepository{

    public Comunidad obtenerComunidad(Long id) { //TODO
        return find(Comunidad.class, id);
    }

    public Long obtenerIdDeComunidadPorNombre(String nombreComunidad) {
        return withTransaction(() -> {
            TypedQuery<Comunidad> query = entityManager().createQuery(
                    "SELECT s FROM Servicio s WHERE s.nombre = :nombre", Comunidad.class);
            query.setParameter("nombre", nombreComunidad);

            Comunidad comunidad = query.getSingleResult();

            if (comunidad != null) {
                return comunidad.getId();
            } else {
                return null;
            }
        });
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

    @Override
    public List buscarTodos() {
        return entityManager().createQuery("from " + Comunidad.class.getName()).getResultList();
    }

    @Override
    public Object buscar(Long id) {
        return entityManager().find(Comunidad.class, id);
    }

    public List<Comunidad> buscarComunidadesDe(long idUsuario) {
        TypedQuery<Comunidad> query = entityManager().createQuery(
                "SELECT r.comunidad FROM Rol r " +
                        "WHERE r.usuario.id = :userId", Comunidad.class);

        query.setParameter("userId", idUsuario);

        return query.getResultList();
    }




}
