package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.notificaciones.medioEnvio.AdapterWhatsapp;
import domain.notificaciones.medioEnvio.MedioNotificacion;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;


public class WhatsApp implements MedioNotificacion {

    AdapterWhatsapp adapter;

    @Override
    public void enviarNotificacionA(Usuario usuario, String texto) {

        adapter.enviarNotificacionA(usuario, texto);
    }
}
