package domain.notificaciones;

import domain.notificaciones.creacion.Cierre;
import domain.notificaciones.creacion.Creacion;
import domain.notificaciones.creacion.NotificacionBuilder;
import domain.notificaciones.creacion.Revision;
import domain.notificaciones.envio.ModoDeRecepcion;
import domain.registro.Usuario;
import domain.incidentes.Incidente;

import java.util.ArrayList;

public class Notificador {

    private static Notificador instancia = null;
    private static NotificacionBuilder builder;


    public static Notificador getInstancia(){
        if(instancia==null) {
            instancia = new Notificador();
            builder = new NotificacionBuilder();
        }

        return instancia;
    }

    public static void creeUnIncidente(Incidente incidente) {
        builder.construirTexto(incidente, new Creacion());
        notificar(incidente);
    }

    public static void cerreUnIncidente(Incidente incidente){
        builder.construirTexto(incidente,new Cierre());
        notificar(incidente);
    }

    public static void pedidoDeRevisionDeIncidente(Incidente incidente){
        builder.construirTexto(incidente, new Revision());
        notificar(incidente);
    }

    public static void notificar(Incidente incidente) {
        Notificacion notificacion = builder.construirNotificacion();

        ArrayList<Usuario> usuariosInteresados = incidente.obtenerUsuariosInteresados();
        notificarUsuarios(usuariosInteresados, notificacion);
    }

    public static void notificarUsuarios(ArrayList<Usuario> usuarios, Notificacion notificacion){
        
        // acá adentro le saca al usuario su medio preferido, modo de recepción y manda la notif por wpp/mail y cuando suceden//sin apuros
        int i;
        for(i=0; i<=usuarios.size(); i++){

            Usuario usuarioTemporal = usuarios.get(i);

           usuarioTemporal.getMedioPreferido().enviarNotificacionA(notificacion, usuarioTemporal);
           usuarioTemporal.getModoRecepcion().recibir(notificacion);

        }
    }
}
