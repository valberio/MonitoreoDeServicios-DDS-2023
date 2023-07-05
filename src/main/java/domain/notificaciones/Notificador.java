package domain.notificaciones;

import domain.notificaciones.creacion.Cierre;
import domain.notificaciones.creacion.Creacion;
import domain.notificaciones.creacion.NotificacionBuilder;
import domain.notificaciones.creacion.Revision;
import domain.registro.Usuario;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
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

    public static void creeUnIncidente(Incidente incidente){
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

    public static Notificacion construirNotificacion() {

        return builder.construirNotificacion(LocalDateTime.now());
    }

    public static void notificar(Incidente incidente) {

        ArrayList<Usuario> usuariosInteresados = incidente.obtenerUsuariosInteresados();
        notificarUsuarios(usuariosInteresados, construirNotificacion());
    }

    public static void notificarUsuarios(ArrayList<Usuario> usuarios, Notificacion notificacion){
        
        // acá adentro le saca al usuario su medio preferido, modo de recepción y manda la notif por wpp/mail y cuando suceden//sin apuros
        int i;
        for(i=0; i<=usuarios.size(); i++){

            Usuario usuario = usuarios.get(i);

            notificar(usuario, notificacion);
        }
    }

    public static void agruparTextoNotificacion(String nuevoTexto) {

        builder.editarTexto(nuevoTexto);
    }


    public static void inicioTextoResumen() {

        builder.construirTexto("Estos son todos los incidentes que ocurrieron mientras no estabas: \n");
    }

    public static void notificar(Usuario usuario) {

        Notificacion notificacion = construirNotificacion();

        usuario.getModoRecepcion().enviarNotificacionA(usuario, notificacion);

    }

    public static void notificar(Usuario usuario, Notificacion notificacion) {
        usuario.getModoRecepcion().enviarNotificacionA(usuario, notificacion);

    }
}

