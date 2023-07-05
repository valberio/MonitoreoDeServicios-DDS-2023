package domain.notificaciones.envio;

import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.notificaciones.creacion.NotificacionBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ValidadorNotificacionAsincronica {
    private static ValidadorNotificacionAsincronica instancia = null;

    public static ValidadorNotificacionAsincronica getInstancia(){
        if(instancia==null) {
            instancia = new ValidadorNotificacionAsincronica();
        }
        return instancia;
    }

    public static boolean cumpleCondiciones(Notificacion notificacion) {

        LocalDateTime ahora = LocalDateTime.now();

        return notificacion.getHoraCreacion().until(ahora, ChronoUnit.HOURS) <=24;

    }
}
