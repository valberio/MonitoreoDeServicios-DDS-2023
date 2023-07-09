package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.registro.Usuario;

public interface MedioNotificacion {


    void enviarNotificacionA(Usuario usuario, Notificacion notificacion);
}
