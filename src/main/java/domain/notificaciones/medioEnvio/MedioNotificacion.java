package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.registro.Usuario;

public interface MedioNotificacion {


    public void enviarNotificacionA(Usuario usuario, Notificacion notificacion);
}
