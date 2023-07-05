package domain.notificaciones;

import domain.registro.Usuario;

public interface MedioNotificacion {


    void enviarNotificacionA(Usuario usuario, Notificacion notificacion);
}
