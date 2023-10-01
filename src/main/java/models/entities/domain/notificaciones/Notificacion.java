package models.entities.domain.notificaciones;

import models.entities.domain.notificaciones.creacion.ContextoDeIncidente;
import models.entities.domain.notificaciones.tiempoDeEnvio.EnviarNotificacionAsincronica;
import models.entities.domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;
import models.entities.domain.notificaciones.tiempoDeEnvio.ModoRecepcion;

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

            case ModoRecepcion.SINCRONICA:
                usuario.getMedioPreferido().enviarNotificacionA(usuario,this.texto);

            case ModoRecepcion.ASINCRONICA:
                if(contextoIncidente == ContextoDeIncidente.CREACION) {

                    EnviarNotificacionAsincronica enviarNotificacionAsincronica = new EnviarNotificacionAsincronica(usuario);
                    enviarNotificacionAsincronica.agregarNotificacionSinEnviar(this);
                }
        }
    }
}