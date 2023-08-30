package domain.notificaciones;

import domain.notificaciones.creacion.ContextoDeIncidente;
import domain.notificaciones.tiempoDeEnvio.Recepcion;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Getter
@Setter
public class Notificacion {

    private String texto;
    private ContextoDeIncidente contextoIncidente;

    private Boolean fueEnviada;

    public LocalDateTime horaCreacion;
    public Notificacion(String texto, LocalDateTime hora) {
        this.texto = texto;
        this.horaCreacion = hora;
    }

    public void enviarseA(Usuario usuario) throws MessagingException {

        switch(usuario.getModoRecepcion())  {

            case SINCRONICA:
                usuario.getMedioPreferido().enviarNotificacionA(usuario,this.texto);

            case ASINCRONICA:
                if(contextoIncidente == ContextoDeIncidente.CREACION) {

                    Recepcion recepcion = new Recepcion(usuario);
                    recepcion.agregarNotificacionSinEnviar(this);
                }
        }
    }
}