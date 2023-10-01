package models.repositories.datos;

import models.entities.domain.servicios.PrestacionDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

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

}
