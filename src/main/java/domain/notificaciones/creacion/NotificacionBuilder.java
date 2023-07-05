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

    public void construirTexto(Incidente incidente, ContextoIncidente contexto) {

        this.texto =  contexto.obtenerTexto(incidente);

    }

    public void editarTexto(String nuevoTexto) {

        this.texto = this.texto + " - " + nuevoTexto + "\n";
    }
}
