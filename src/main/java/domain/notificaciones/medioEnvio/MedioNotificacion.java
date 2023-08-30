package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.registro.Usuario;

import javax.mail.MessagingException;

public interface MedioNotificacion {


    public void enviarNotificacionA(Usuario usuario, String texto) throws MessagingException;
}
