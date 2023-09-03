package domain.registro;


import domain.Persistente;
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
import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario extends Persistente {

    @Column(name="usuario")
    private String usuario;
    @Column(name="usuario")
    private String email;
    @Embedded
    private Contrasenia contrasenia;
    @Column(name="telefono")
    private String numeroTelefono;
    @Column(name="bloqueado")
    private Boolean bloqueado;
    @Transient
    private Ubicacion localizacion;
    @Enumerated(EnumType.STRING)
    private MedioNotificacion medioPreferido; // Email o Wpp
    @Enumerated(EnumType.STRING)
    private ModoRecepcion modoRecepcion; // Sincronico o asincronico

    @ElementCollection
    @CollectionTable(name = "usuario_horario_disponible", joinColumns = @JoinColumn(name="usuario_id"))
    private List<LocalTime> horariosDisponibles;

    @OneToMany
    private List<Rol> roles;
    @ManyToMany
    private List<Entidad> entidadesDeInteres;

    private Map<PrestacionDeServicio, Identificador> impactosDePrestaciones;

    public Usuario() {
        horariosDisponibles = new ArrayList<>();
        roles = new ArrayList<>();
        entidadesDeInteres = new ArrayList<>();
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



