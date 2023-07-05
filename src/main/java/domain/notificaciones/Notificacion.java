package domain.notificaciones;

import domain.notificaciones.creacion.ContextoIncidente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notificacion {

    private String texto;
    private ContextoIncidente contextoIncidente;

    public LocalDateTime horaCreacion;
    public Notificacion(String texto, LocalDateTime hora) {
        this.texto = texto;
        this.horaCreacion = hora;
    }
}