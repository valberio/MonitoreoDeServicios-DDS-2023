package domain.notificaciones.creacion;

import domain.incidentes.Incidente;
import domain.notificaciones.Notificacion;

public class NotificacionBuilder {

    private String texto;

    public Notificacion construirNotificacion() {

        return new Notificacion(texto);
    }
    public void construirTexto(Incidente incidente, ContextoIncidente contexto) {

        this.texto =  contexto.obtenerTexto(incidente);

    }
}
