package domain.registro;

import datos.RepositorioUsuarios;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Registro {

    private static Registro instancia = null;
    private RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();

    public static Registro  getInstancia(){
        if(instancia==null) {
            instancia = new Registro();
        }

        return instancia;
    }
    public void registrarUsuario(String usuario, Contrasenia contrasenia, String email, PreferenciaEnvioNotificacion preferenciaEnvioNotificacion) throws IOException {
        if (this.noEstaRegistrado(usuario) && contrasenia.esValida()) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombreDeUsuario(usuario);
            nuevoUsuario.setContrasenia(contrasenia);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPreferencias(preferenciaEnvioNotificacion);
            RepositorioUsuarios.agregarUnUsuario(nuevoUsuario);
            contrasenia.mostrarFuerza();
        }
    }

    public void registrarUsuario(Usuario usuario) {
        if (this.noEstaRegistrado(usuario.getNombreDeUsuario()) && usuario.getContrasenia().esValida()) {
            this.repositorioUsuarios.agregarUsuario(usuario); // Para que se pueda persistir
            usuario.getContrasenia().mostrarFuerza();
        }
    }

    /*public boolean noEstaRegistrado(String usuario){
        return RepositorioUsuarios.getUsuariosRegistrados().stream().noneMatch(Usuario -> Objects.equals(Usuario.getNombreDeUsuario(), usuario));
    }*/

    public boolean noEstaRegistrado(String usuario) {
        return this.repositorioUsuarios.noEstaRegistrado(usuario);
    }


}

