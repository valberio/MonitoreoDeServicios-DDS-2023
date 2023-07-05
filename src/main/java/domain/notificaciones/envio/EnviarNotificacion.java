package domain.notificaciones.envio;

import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnviarNotificacion {

    private Usuario usuario;
    private Notificacion notificacion;

    private Notificador notificador = Notificador.getInstancia();


    
    public void ejecutar() {

        if (ValidadorNotificacionAsincronica.cumpleCondiciones(notificacion)){
            Notificador.agruparTextoNotificacion(notificacion.getTexto());
        }
    }

    public EnviarNotificacion(Usuario usuario, Notificacion notificacion) {
        this.usuario = usuario;
        this.notificacion = notificacion;
    }
}
