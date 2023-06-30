package domain.notificaciones;

import domain.notificaciones.creacion.ContextoIncidente;

public class Notificacion {

    private String texto;
    private ContextoIncidente contextoIncidente;

    public Notificacion(String texto) {
        this.texto = texto;
    }
}