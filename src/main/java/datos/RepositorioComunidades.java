package datos;

import domain.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepositorioComunidades implements WithSimplePersistenceUnit {

    public void agregarComunidad(Comunidad comunidad) {
        entityManager().persist(comunidad);
    }

    public void actualizarComunidad(String idAModificar) {

        //TODO
    }

    public void eliminarComunidad(Comunidad comunidad) {
        entityManager().remove(comunidad);
    }

    public Comunidad obtenerComunidad(Long id) { //TODO
        return find(Comunidad.class, id);
    }



}
