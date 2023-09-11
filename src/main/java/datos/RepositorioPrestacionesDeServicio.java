package datos;

import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class RepositorioPrestacionesDeServicio implements WithSimplePersistenceUnit {

    public void agregarPrestacion(PrestacionDeServicio prestacion) {
        withTransaction( () -> {
            entityManager().persist(prestacion); });
    }

    public void eliminarPrestacion(PrestacionDeServicio prestacion) {
        entityManager().remove(prestacion);
    }

}
