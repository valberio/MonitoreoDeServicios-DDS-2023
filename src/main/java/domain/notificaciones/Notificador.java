package domain.notificaciones;

import datos.RepositorioIncidentes;
import domain.Localizacion.Localizacion;
import domain.notificaciones.creacion.Cierre;
import domain.notificaciones.creacion.Creacion;
import domain.notificaciones.creacion.NotificacionBuilder;
import domain.notificaciones.creacion.Revision;
import domain.notificaciones.tiempoDeEnvio.EnviarNotificacion;
import domain.registro.Usuario;
import domain.incidentes.Incidente;
import lombok.Getter;
import lombok.Setter;

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

    public void creeUnIncidente(Incidente incidente){
        builder.construirTexto(incidente, new Creacion());
        notificar(incidente);
    }

    public void cerreUnIncidente(Incidente incidente){
        builder.construirTexto(incidente,new Cierre());
        notificar(incidente);
    }

    public void pedidoDeRevisionDeIncidente(Incidente incidente){
        builder.construirTexto(incidente, new Revision());
        notificar(incidente);
    }

    public void enviarSugerenciasRevisionA(Usuario usuario){

        Localizacion localizacionUsuario = usuario.getLocalizacion();
        List<Incidente> incidentesCercanos;
        incidentesCercanos = RepositorioIncidentes.getInstance().incidentes.stream()
                .filter(incidente -> incidente.getServicioAfectado().getEstablecimiento().getUbicacionGeografica().
                        estasCercaDe(localizacionUsuario)).
                        collect(Collectors.toList());

        for (Incidente incidenteCercano : incidentesCercanos) {
            this.pedidoDeRevisionDeIncidente(incidenteCercano);
        }
    }
    public static Notificacion construirNotificacion() {

        return builder.construirNotificacion(LocalDateTime.now());
    }

    public static void notificar(Incidente incidente) {

        ArrayList<Usuario> usuariosInteresados = incidente.obtenerUsuariosInteresados();
        notificarUsuarios(usuariosInteresados, construirNotificacion());
    }

    public static void notificarUsuarios(ArrayList<Usuario> usuarios, Notificacion notificacion){
        
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

    public static void enviarNotificacionResumen(ArrayList<EnviarNotificacion> notificacionesSinEnviar) {

        inicioTextoResumen();
        Usuario usuarioInteresado = null;


        for (EnviarNotificacion comando : notificacionesSinEnviar) {

            usuarioInteresado = comando.getUsuario();
            comando.ejecutar();

        }

        notificar(usuarioInteresado);
    }
}

