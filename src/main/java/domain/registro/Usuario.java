package domain.registro;


import domain.comunidad.Comunidad;
import domain.incidentes.Incidente;
import domain.notificaciones.Notificador;
import domain.notificaciones.medioEnvio.MedioNotificacion;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import domain.roles.Rol;
import domain.services.georef.entities.Ubicacion;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter

public class Usuario {
    private String usuario;
    private String email;
    private Contrasenia contrasenia;
    private String numeroTelefono;
    private Boolean bloqueado;
    private Ubicacion localizacion;
    private MedioNotificacion medioPreferido; // Email o Wpp
    private ModoRecepcion modoRecepcion; // Sincronico o asincronico
    private ArrayList<LocalTime> horariosDisponibles;
    ArrayList<Rol> roles = new ArrayList<>();
    ArrayList<Entidad> entidadesDeInteres = new ArrayList<>();

    private Map<PrestacionDeServicio, Identificador> impactosDePrestaciones;

    public Usuario(String usuario, Contrasenia contrasenia, String email, PreferenciaEnvioNotificacion preferenciaNotificaciones) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.bloqueado = false;
        this.setPreferencias(preferenciaNotificaciones);
        horariosDisponibles = new ArrayList<>();
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setContrasenia(Contrasenia contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocalizacion(Ubicacion localizacion) {
        this.localizacion = localizacion;
    }

    public void setMedioPreferido(MedioNotificacion medioPreferido) {
        this.medioPreferido = medioPreferido;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void setModoRecepcion(ModoRecepcion modoRecepcion) {
        this.modoRecepcion = modoRecepcion;
    }

    public void setHorariosDisponibles(List<LocalTime> horariosDisponibles) {
        this.horariosDisponibles = horariosDisponibles;
    }

    public void setImpactosDePrestaciones(Map<PrestacionDeServicio, Identificador> impactosDePrestaciones) {
        this.impactosDePrestaciones = impactosDePrestaciones;
    }

    public void setEntidadesDeInteres(List<Entidad> entidadesDeInteres) {
        this.entidadesDeInteres = entidadesDeInteres;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public void configurarHorariosDisponibles(LocalTime... horario) {
        horariosDisponibles.addAll(List.of(horario));
    }

    public void setPreferencias(PreferenciaEnvioNotificacion preferencia) {

        medioPreferido = preferencia.getMedioNotificacion();
        modoRecepcion = preferencia.getModoRecepcion();

    }

    public void agregarEntidadesDeInteres(Entidad... entidad) {

        entidadesDeInteres.addAll(List.of(entidad));
    }

    public ArrayList<Servicio> serviciosDeInteres() {

        return entidadesDeInteres.stream().flatMap(entidad -> entidad.serviciosConIncidente()).collect(Collectors.toCollection(ArrayList::new));
    }


    public boolean teInteresa(PrestacionDeServicio servicioAfectado) {

        return this.serviciosDeInteres().stream().anyMatch(servicio -> servicioAfectado.getServicio().equals(servicio));
    }

    public boolean teInteresa(Incidente incidente) {

        return this.teInteresa(incidente.getServicioAfectado()) || this.estasEn(incidente.getComunidadDondeSeReporta());
    }

    public boolean estasEn(Comunidad comunidad) {

        return roles.stream().anyMatch(rol -> rol.getComunidad().equals(comunidad));
    }

    public void modificarLocalizacion(Ubicacion nuevaLocalizacion) throws MessagingException {
        this.localizacion = nuevaLocalizacion;
        Notificador notificadorRevisiones = new Notificador();
        notificadorRevisiones.enviarSugerenciasRevisionA(this);
    }

}



