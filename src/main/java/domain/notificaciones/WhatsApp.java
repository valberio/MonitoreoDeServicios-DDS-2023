package domain.notificaciones;

import domain.registro.Usuario;

public class WhatsApp implements MedioNotificacion{

    AdapterWhatsapp adapter;

    @Override
    public void enviarNotificacionA(Usuario usuario, Notificacion notificacion) {

    }
}
