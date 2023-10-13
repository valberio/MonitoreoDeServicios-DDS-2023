package models.repositories.datos;

import models.entities.domain.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.domain.registro.Usuario;
import models.entities.domain.servicios.PrestacionDeServicio;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

    public void actualizar(Comunidad comunidad) {
        withTransaction(() -> { entityManager().merge(comunidad); });
    }
}
