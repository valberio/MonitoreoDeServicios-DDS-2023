package models.repositories.datos;

import models.entities.domain.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class RepositorioServicios implements WithSimplePersistenceUnit {

    public void agregarServicio(Servicio servicio) {
        withTransaction( () -> {
            entityManager().persist(servicio); });
    }

    public void eliminarServicio(Servicio servicio) {
        entityManager().remove(servicio);
    }
}
