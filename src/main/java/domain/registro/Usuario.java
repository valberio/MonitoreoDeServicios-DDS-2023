package domain.registro;

import domain.Localizacion.Localizacion;
import domain.comunidad.Comunidad;
import domain.incidentes.Incidente;
import domain.notificaciones.MedioNotificacion;
import domain.notificaciones.Notificador;
import domain.notificaciones.envio.PreferenciaEnvioNotificacion;
import domain.notificaciones.NotificadorRevisiones;
import domain.notificaciones.envio.Recepcion;
import domain.roles.Rol;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class Usuario {
    private String usuario;
    private String email;
    private Contrasenia contrasenia;
    private Boolean bloqueado;
    private Localizacion localizacion;
    private MedioNotificacion medioPreferido; // Email o Wpp
    private Recepcion modoRecepcion; // Sincronico o asincronico
    private ArrayList<LocalTime> horariosDisponibles; //LocalTime horaActual = LocalTime.of(15, 30, 0);
    ArrayList<Rol> roles = new ArrayList<>();
    ArrayList<Entidad> entidadesDeInteres = new ArrayList<>();

    public Usuario(String usuario, Contrasenia contrasenia, String email, PreferenciaEnvioNotificacion preferenciaNotificaciones) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.bloqueado = false;
        this.setPreferencias(preferenciaNotificaciones);
        horariosDisponibles = new ArrayList<>();
    }

    public void setPreferencias(PreferenciaEnvioNotificacion preferencia) {

        medioPreferido = preferencia.getMedioNotificacion();
        modoRecepcion = preferencia.getRecepcionNotificacion();

    }

    public void agregarEntidadesDeInteres (Entidad ... entidad) {

        entidadesDeInteres.addAll(List.of(entidad));
    }
    public ArrayList<Servicio> serviciosDeInteres() {

        return entidadesDeInteres.stream().flatMap(entidad -> entidad.serviciosConIncidente()).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean teInteresa(PrestacionDeServicio servicioAfectado) {

        return this.serviciosDeInteres().stream().anyMatch(servicio->servicioAfectado.getServicio().equals(servicio));
    }

    public boolean estasEn(Comunidad comunidad) {

        return roles.stream().anyMatch(rol->rol.getComunidad().equals(comunidad));
    }

    public void modificarLocalizacion(Localizacion nuevaLocalizacion){
        this.localizacion = nuevaLocalizacion;
        NotificadorRevisiones notificadorRevisiones = new NotificadorRevisiones();
        notificadorRevisiones.enviarSugerenciasRevisionA(this);
    }

    public void informarIncidente(Incidente unIncidente){
        Notificador notificador = new Notificador() ;
        notificador.getInstancia().creeUnIncidente(unIncidente);
        unIncidente.comunidadDondeSeReporta.seInformoUnIncidente(unIncidente);
    }

    public void resolverIncidente(Incidente unIncidente){
        Notificador notificador = new Notificador();
        notificador.getInstancia().cerreUnIncidente(unIncidente);
        unIncidente.comunidadDondeSeReporta.seResolvioUnIncidente(unIncidente);
    }
}
