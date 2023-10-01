package models.repositories.datos;

import models.entities.domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import models.entities.domain.registro.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter

public class RepositorioUsuarios implements WithSimplePersistenceUnit {

    private static RepositorioUsuarios instancia = null;

    //Esto se usa unicamente para no romper los tests
    private static List<Usuario> usuariosRegistrados = new ArrayList<Usuario>();


    public static RepositorioUsuarios getInstance(){
        if(instancia==null) {
            instancia = new RepositorioUsuarios();
        }

        return instancia;
    }

    public static void agregarUnUsuario(Usuario usuario) {

        usuariosRegistrados.add(usuario);
    }

    public static List<Usuario> getUsuariosRegistrados() { return usuariosRegistrados; }
    public void agregarUsuario(Usuario usuario ) {
        withTransaction( () -> {
            if(noEstaRegistrado(usuario.getNombreDeUsuario())) {
                entityManager().persist(usuario);
            }


        });
    }

    public boolean noEstaRegistrado(String nombreUsuario) {

       List usuarios = this.filtrarPorNombre(nombreUsuario);

        return usuarios.isEmpty();
    }

    public List usuariosConNotificacionesAsincronicas() {

       return entityManager().createQuery("from Usuario where modoRecepcion = :asincronica").
                setParameter("asincronica", ModoRecepcion.ASINCRONICA).getResultList();

    }

    public List filtrarPorNombre(String nombreUsuario) {
        List usuarios = entityManager().createQuery("from Usuario where nombreDeUsuario = :nombre")
                .setParameter("nombre", nombreUsuario).getResultList();

        return usuarios;
    }

    public List getUsuariosPersistentes() {

        return entityManager().createQuery("from Usuario").getResultList();
    }

    public void eliminarUsuario(Usuario usuario) {
        entityManager().remove(usuario);
    }
}
