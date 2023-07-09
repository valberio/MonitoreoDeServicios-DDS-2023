package domain.incidentes;

import domain.comunidad.Comunidad;
import domain.notificaciones.Notificador;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;

import java.time.LocalDateTime;

public class IncidenteFactory {

    Notificador notificador;

    public Incidente crearIncidente(PrestacionDeServicio prestacionDeServicio, Usuario usuario, Comunidad comunidad) {

        Incidente nuevoIncidente = new Incidente(prestacionDeServicio,usuario, LocalDateTime.now(), comunidad);

        Notificador.getInstancia().creeUnIncidente(nuevoIncidente);

        nuevoIncidente.getComunidadDondeSeReporta().seInformoUnIncidente(nuevoIncidente);

        return nuevoIncidente;
    }
}

