package domain.notificaciones.creacion;

import domain.incidentes.Incidente;
import domain.notificaciones.Notificacion;

import java.time.LocalDateTime;

public class NotificacionBuilder {

    private String texto;

    public Notificacion construirNotificacion(LocalDateTime hora) {

        return new Notificacion(texto, hora);
    }

    public void construirTexto(String texto) {

            this.texto = texto;

    }

    public void construirTexto(Incidente incidente, ContextoDeIncidente contexto) {

        switch(contexto) {

            case CREACION -> this.texto ="Un usuario reporto que no funciona el  " + incidente.getServicioAfectado().obtenerTextoRelevante();

            case SUGERENCIA_DE_REVISION -> this.texto = "¡Hola! Por favor, te solicitamos revisar el funcionamiento del servicio " + incidente.getServicioAfectado() + " en " + incidente.getLocalizacion();

            case CIERRE -> this.texto = "¡Funciona nuevamente el incidente en " + incidente.getServicioAfectado().obtenerTextoRelevante() + "!";
        }

    }

    public void editarTexto(String nuevoTexto) {

        this.texto = this.texto + " - " + nuevoTexto + "\n";
    }
}
