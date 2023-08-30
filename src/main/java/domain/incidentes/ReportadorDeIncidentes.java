package domain.incidentes;

import datos.RepositorioUsuarios;
import domain.comunidad.Comunidad;
import domain.notificaciones.Notificador;
import domain.registro.Registro;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
@Getter
@Setter
public class ReportadorDeIncidentes {
    private static ReportadorDeIncidentes instancia = null;
    public static ReportadorDeIncidentes getInstancia(){
        if(instancia==null) {
            instancia = new ReportadorDeIncidentes();
        }
        return instancia;
    }

    Notificador notificador = Notificador.getInstancia();

    public Incidente crearIncidente(PrestacionDeServicio prestacionDeServicio, Usuario usuario, Comunidad comunidad, String observacion) throws MessagingException {

        Incidente nuevoIncidente = new Incidente(prestacionDeServicio,usuario, LocalDateTime.now(), comunidad, observacion);

        notificador.creeUnIncidente(nuevoIncidente);

        comunidad.seInformoUnIncidente(nuevoIncidente);

        return nuevoIncidente;
    }
}

