package domain.notificaciones.tiempoDeEnvio;

import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.notificaciones.creacion.Creacion;
import domain.registro.Usuario;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

public class Recepcion implements Job {

    private ModoRecepcion modo;
    private ArrayList<EnviarNotificacion> notificacionesSinEnviar;

    private Notificador notificador = Notificador.getInstancia();

    public Recepcion(ModoRecepcion modo) {
        this.modo = modo;
        notificacionesSinEnviar = new ArrayList<>();
    }

    public void enviarNotificacionA(Usuario usuario, Notificacion notificacion) {

        switch(this.modo) {
            case SINCRONICA:
                usuario.getMedioPreferido().enviarNotificacionA(usuario, notificacion);
                break;

            case ASINCRONICA:
                // Para tomar unicamente las notificaciones que corresponden a la apertura de un incidente
                if(notificacion.getContextoIncidente() instanceof Creacion) {
                    notificacionesSinEnviar.add(new EnviarNotificacion(usuario,notificacion));
                }


                break;
        }

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Notificador.enviarNotificacionResumen(this.notificacionesSinEnviar);
    }
}
