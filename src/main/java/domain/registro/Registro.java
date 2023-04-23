package domain.registro;

import java.util.ArrayList;
import java.util.Objects;

public class Registro {

    //momentaneo hasta que se puedan implementar correctamente los usuariosYaRegistrados
    private ArrayList<Usuario> usuariosRegistrados;
    public void registrarUsuario(String usuario, Contrasenia contrasenia, String email) {
        if (this.noEstaRegistrado(usuario) && contrasenia.esValida()) {
            Usuario nuevoUsuario = new Usuario(usuario, contrasenia, email);
            this.usuariosRegistrados.add(nuevoUsuario);
            contrasenia.mostrarFuerza();
        }
    }

    public boolean noEstaRegistrado(String usuario){
        return usuariosRegistrados.stream().noneMatch(Usuario -> Objects.equals(Usuario.getUsuario(), usuario));
    }

}
