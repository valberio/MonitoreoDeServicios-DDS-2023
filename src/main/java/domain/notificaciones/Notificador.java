package domain.notificaciones;

import datos.RepositorioIncidentes;

import domain.notificaciones.creacion.ContextoDeIncidente;
import domain.notificaciones.creacion.*;
import domain.notificaciones.tiempoDeEnvio.ValidadorNotificacionAsincronica;
import domain.registro.Usuario;
import domain.incidentes.Incidente;
import domain.services.georef.entities.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void creeUnIncidente(Incidente incidente) throws MessagingException {

        builder.construirTexto(incidente, ContextoDeIncidente.CREACION);
        this.notificar(incidente);
    }

    public void cerreUnIncidente(Incidente incidente) throws MessagingException {

        builder.construirTexto(incidente,ContextoDeIncidente.CIERRE);
        this.notificar(incidente);
    }

    public void pedidoDeRevisionDeIncidente(Incidente incidente) throws MessagingException {

        builder.construirTexto(incidente, ContextoDeIncidente.SUGERENCIA_DE_REVISION);
        this.notificar(incidente);
    }

    public void enviarSugerenciasRevisionA(Usuario usuario) throws MessagingException {

        Ubicacion localizacionUsuario = usuario.getLocalizacion();
        List<Incidente> incidentesCercanos;
        incidentesCercanos = (List<Incidente>) RepositorioIncidentes.getInstance().filtrarPorUbicacionCercana(usuario.getLocalizacion());

        for (Incidente incidenteCercano : incidentesCercanos) {
            this.pedidoDeRevisionDeIncidente(incidenteCercano);
        }
    }

    public void notificar(Incidente incidente) throws MessagingException {

        ArrayList<Usuario> usuariosInteresados = incidente.obtenerUsuariosInteresados();
        this.notificarUsuarios(usuariosInteresados, builder.construirNotificacion(LocalDateTime.now()));
    }

    public void notificarUsuarios(ArrayList<Usuario> usuarios, Notificacion notificacion) throws MessagingException {
        
        int i;
        if(!usuarios.isEmpty()) {
            for(i=0; i<usuarios.size(); i++){

                Usuario usuario = usuarios.get(i);

                notificacion.enviarseA(usuario);
            }
        }

    }

    public void notificar(Usuario usuario) throws MessagingException {

        Notificacion notificacion = builder.construirNotificacion(LocalDateTime.now());
        notificacion.enviarseA(usuario);

    }

    public void enviarNotificacionResumenA(Usuario usuario, ArrayList<Notificacion> notificacionesSinEnviar) throws MessagingException {

        builder.construirTexto("Estos son todos los incidentes que ocurrieron mientras no estabas: \n");

        for (Notificacion notificacion : notificacionesSinEnviar) {

            if (ValidadorNotificacionAsincronica.cumpleCondiciones(notificacion)){
                builder.editarTexto(notificacion.getTexto());
            }

        }

       this.notificar(usuario);
    }
}

