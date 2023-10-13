package models.repositories.datos;

import models.entities.domain.comunidad.Comunidad;
import models.entities.domain.servicios.PrestacionDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.TypedQuery;

public class RepositorioPrestacionesDeServicio implements WithSimplePersistenceUnit {

    public void agregarPrestacion(PrestacionDeServicio prestacion) {
        withTransaction( () -> {
            entityManager().persist(prestacion); });
    }

    public void actualizarPrestacion(PrestacionDeServicio prestacion) {

        withTransaction(() -> { entityManager().merge(prestacion); });
    }

    public void eliminarPrestacion(PrestacionDeServicio prestacion) {
        withTransaction( () -> {
            entityManager().remove(prestacion); });
    }

    public PrestacionDeServicio obtenerPrestacion(Long id) {
        return find(PrestacionDeServicio.class, id);
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
