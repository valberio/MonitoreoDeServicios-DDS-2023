package datos;

import domain.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.With;

public class RepositorioServicios implements WithSimplePersistenceUnit {

    public void agregarServicio(Servicio servicio) {
        withTransaction( () -> {
            entityManager().persist(servicio); });
    }
}
