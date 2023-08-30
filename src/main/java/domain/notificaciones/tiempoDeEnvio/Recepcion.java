package domain.notificaciones.tiempoDeEnvio;

import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.notificaciones.creacion.ContextoDeIncidente;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import javax.mail.MessagingException;
import java.util.ArrayList;

@Getter

public class Recepcion implements Job {
    private ArrayList<Notificacion> notificacionesSinEnviar;
    private Usuario usuario;

    public Recepcion(Usuario usuario) {

        notificacionesSinEnviar = new ArrayList<>();

        this.usuario = usuario;
    }

    public void agregarNotificacionSinEnviar(Notificacion notificacion) {

        notificacionesSinEnviar.add(notificacion);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        Notificador notificador = Notificador.getInstancia();

        try {
            notificador.enviarNotificacionResumenA(usuario, this.notificacionesSinEnviar);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
