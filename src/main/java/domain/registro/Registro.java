package domain.registro;

import domain.notificaciones.Notificador;
import domain.notificaciones.envio.PreferenciaEnvioNotificacion;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
public class Registro {

    private static Registro instancia = null;

    public static Registro  getInstancia(){
        if(instancia==null) {
            instancia = new Registro();
        }

        return instancia;
    }

    //momentaneo hasta que se puedan implementar correctamente los usuariosYaRegistrados
    private ArrayList<Usuario> usuariosRegistrados;
    public void registrarUsuario(String usuario, Contrasenia contrasenia, String email, PreferenciaEnvioNotificacion preferenciaEnvioNotificacion) throws IOException {
        if (this.noEstaRegistrado(usuario) && contrasenia.esValida()) {
            Usuario nuevoUsuario = new Usuario(usuario, contrasenia, email, preferenciaEnvioNotificacion);
            this.usuariosRegistrados.add(nuevoUsuario);
            contrasenia.mostrarFuerza();
        }
    }

    public boolean noEstaRegistrado(String usuario){
        return usuariosRegistrados.stream().noneMatch(Usuario -> Objects.equals(Usuario.getUsuario(), usuario));
    }

}

