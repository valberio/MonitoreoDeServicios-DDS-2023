package models.repositories.datos;

import models.entities.domain.entidades.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import models.entities.domain.registro.Usuario;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepositorioEntidades implements WithSimplePersistenceUnit {

    public void agregarEntidad(Entidad entidad) {

            EntityTransaction tx = entityManager().getTransaction();
            tx.begin();
            entityManager().persist(entidad);
            tx.commit();
        }

        public void eliminarEntidad(Entidad entidad) {
            entityManager().remove(entidad);
        }

        public Entidad obtenerEntidad(Long id) {
            return find(Entidad.class, id);
        }

    public void actualizar(Entidad entidad) {
        withTransaction(() -> { entityManager().merge(entidad); });
    }

    public List<String> buscarTodosLosNombresDeEntidades() {
        String jpql = "SELECT e.nombre FROM Entidad e";
        return entityManager().createQuery(jpql, String.class).getResultList();
    }

    public List<Entidad> buscarTodas() {

        return entityManager().createQuery("from Entidad").getResultList();


    }

    public List filtrarPorNombre(String nombreEntidad) {
        List entidades = entityManager().createQuery("from Entidad where nombre = :nombre")
                .setParameter("nombre", nombreEntidad).getResultList();

        return entidades;
    }
}


