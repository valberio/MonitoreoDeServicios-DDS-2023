package domain.incidentes;

import domain.comunidad.Comunidad;
import domain.notificaciones.Notificador;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;

import java.time.LocalDateTime;

public class ReportadorDeIncidentes {

    Notificador notificador = Notificador.getInstancia();

    public Incidente crearIncidente(PrestacionDeServicio prestacionDeServicio, Usuario usuario, Comunidad comunidad, String observacion) {

        Incidente nuevoIncidente = new Incidente(prestacionDeServicio,usuario, LocalDateTime.now(), comunidad, observacion);

        notificador.creeUnIncidente(nuevoIncidente);

        comunidad.seInformoUnIncidente(nuevoIncidente);

        return nuevoIncidente;
    }
}

