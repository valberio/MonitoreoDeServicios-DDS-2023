package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.registro.Usuario;

public interface AdapterWhatsapp  {

    public void enviarNotificacionA(Usuario usuario, Notificacion notificacion);

}
