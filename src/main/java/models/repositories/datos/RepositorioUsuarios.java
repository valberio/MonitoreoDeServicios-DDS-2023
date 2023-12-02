package models.repositories.datos;


import models.entities.domain.incidentes.Incidente;
import models.entities.domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import models.entities.domain.registro.Usuario;
import server.Server;


import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter

public class RepositorioUsuarios implements WithSimplePersistenceUnit, ICrudRepository {

    EntityManager entityManager = Server.createEntityManager();

    private static RepositorioUsuarios instancia = null;

    //Esto se usa unicamente para no romper los tests
    private static List<Usuario> usuariosRegistrados = new ArrayList<Usuario>();


    public static RepositorioUsuarios getInstance(){
        if(instancia==null) {
            instancia = new RepositorioUsuarios();
        }

        return instancia;
    }

    @Override
    public List buscarTodos() {
        return entityManager.createQuery("from " + Usuario.class.getName()).getResultList();
    }

    @Override
    public Object buscar(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public void guardar(Object o) {

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(o);
        tx.commit();
    }

    @Override
    public void eliminar(Object o) {
        entityManager.remove(o);
    }

    @Override
    public void actualizar(Object o) {
        withTransaction(() -> { entityManager.merge(o);});
    }


    public static List<Usuario> getUsuariosRegistrados() { return usuariosRegistrados; }
    public void agregarUsuario(Usuario usuario ) {
        withTransaction( () -> {
            if(noEstaRegistrado(usuario.getNombreDeUsuario())) {
                entityManager.persist(usuario);
            }

        });
    }

    public boolean noEstaRegistrado(String nombreUsuario) {

        List usuarios = this.filtrarPorNombre(nombreUsuario);

        return usuarios.isEmpty();
    }

    public List usuariosConNotificacionesAsincronicas() {

        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.modoRecepcion = :asincronica", Usuario.class);

        query.setParameter("asincronica", ModoRecepcion.ASINCRONICA);
        return query.getResultList() != null ? query.getResultList() : null;
    }

    public List filtrarPorNombre(String nombreUsuario) {
        List usuarios = entityManager.createQuery("FROM Usuario WHERE nombreDeUsuario = :nombre")
                .setParameter("nombre", nombreUsuario).getResultList();

        return usuarios;
    }


}