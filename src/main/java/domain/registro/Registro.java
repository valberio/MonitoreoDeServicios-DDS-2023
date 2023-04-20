package domain.registro;

public class Registro {

    public void registrarUsuario(String usuario, Contrasenia contrasenia, String email){
        Usuario nuevoUsuario = new Usuario(usuario, contrasenia, email);
    }
}
