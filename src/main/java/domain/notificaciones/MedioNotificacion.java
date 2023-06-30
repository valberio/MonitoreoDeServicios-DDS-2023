package domain.notificaciones;

import domain.registro.Usuario;

public interface MedioNotificacion {

    public void enviarNotificacionA();

    void enviarNotificacionA(Notificacion notificacion, Usuario usuarioTemporal);
}
