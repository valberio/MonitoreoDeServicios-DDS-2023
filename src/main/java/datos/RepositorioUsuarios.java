package datos;

import domain.registro.Usuario;
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
            entityManager().persist(usuario); });
    }

    public boolean estaRegistrado(String nombreUsuario) {

        Usuario usuario = entityManager().find(Usuario.class, nombreUsuario);

        return usuario!=null;
    };


}
