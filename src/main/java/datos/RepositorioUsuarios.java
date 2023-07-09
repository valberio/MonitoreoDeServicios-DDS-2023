package datos;

import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter

public class RepositorioUsuarios {

    private static RepositorioUsuarios instancia = null;

    private static ArrayList<Usuario> usuariosRegistrados;


    public static RepositorioUsuarios getInstance(){
        if(instancia==null) {
            instancia = new RepositorioUsuarios();
            usuariosRegistrados = new ArrayList<>();
        }

        return instancia;
    }

    public static void agregarUnUsuario(Usuario usuario) {

        usuariosRegistrados.add(usuario);
    }

    public static ArrayList<Usuario> getUsuariosRegistrados() {

        return usuariosRegistrados;
    }

}
