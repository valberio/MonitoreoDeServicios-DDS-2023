package domain.notificaciones;

import domain.notificaciones.medioEnvio.AdapterWhatsapp;
import domain.notificaciones.medioEnvio.MedioNotificacion;
import domain.registro.Usuario;

public class WhatsApp implements MedioNotificacion {

    AdapterWhatsapp adapter;

    @Override
    public void enviarNotificacionA(Usuario usuario, Notificacion notificacion) {

    }
}
