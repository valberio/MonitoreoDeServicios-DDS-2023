package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.notificaciones.medioEnvio.AdapterWhatsapp;
import domain.notificaciones.medioEnvio.MedioNotificacion;
import domain.registro.Usuario;

public class WhatsApp implements MedioNotificacion {

    AdapterWhatsapp adapter;

    @Override
    public void enviarNotificacionA(Usuario usuario, Notificacion notificacion) {

        adapter.enviarNotificacionA(usuario, notificacion);
    }
}
