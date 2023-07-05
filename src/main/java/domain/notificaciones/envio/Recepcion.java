package domain.notificaciones.envio;

import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.notificaciones.creacion.Creacion;
import domain.registro.Usuario;

import java.util.ArrayList;

public class Recepcion {

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
                //2 horarios
                break;
        }

    }

    public void enviarNotificacionResumen() {

        Notificador.inicioTextoResumen();
        Usuario usuarioInteresado = null;


        for (EnviarNotificacion comando : notificacionesSinEnviar) {

            usuarioInteresado = comando.getUsuario();
            comando.ejecutar();

        }

       Notificador.notificar(usuarioInteresado);
    }
}
