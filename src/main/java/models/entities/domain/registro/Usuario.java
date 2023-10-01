package models.entities.domain.registro;


import converters.MedioNotificacionAttributeConverter;
import models.entities.domain.Persistente;
import models.entities.domain.comunidad.Comunidad;
import models.entities.domain.incidentes.Incidente;
import models.entities.domain.notificaciones.Notificador;
import models.entities.domain.notificaciones.medioEnvio.MedioNotificacion;
import models.entities.domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import models.entities.domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import models.entities.domain.roles.Rol;
import models.entities.domain.services.georef.entities.Ubicacion;
import models.entities.domain.servicios.PrestacionDeServicio;
import models.entities.domain.servicios.RolFrenteAPrestacion;
import models.entities.domain.servicios.Servicio;
import models.entities.domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.mail.MessagingException;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario extends Persistente {
    @Column(name="nombreDeUsuario")
    private String nombreDeUsuario;
    @Column(name="email")
    private String email;
    @Embedded
    private Contrasenia contrasenia;
    @Column(name="telefono")
    private String numeroTelefono;
    @Column(name="bloqueado")
    private Boolean bloqueado;
    @Transient
    private Ubicacion localizacion;
    @Convert(converter = MedioNotificacionAttributeConverter.class)
    @Column(name = "medioNotificacion")
    @Nullable
    private MedioNotificacion medioPreferido; // Email o Wpp
    @Enumerated(EnumType.STRING)
    private ModoRecepcion modoRecepcion; // Sincronico o asincronico
    @ElementCollection
    @CollectionTable(name = "usuario_horario_disponible", joinColumns = @JoinColumn(name="usuario_id"))
    private List<LocalTime> horariosDisponibles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Rol> roles;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Entidad> entidadesDeInteres;

    @OneToMany(mappedBy="usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<RolFrenteAPrestacion> rolFrenteAPrestaciones;

    public Usuario() {
        horariosDisponibles = new ArrayList<>();
        roles = new ArrayList<>();
        entidadesDeInteres = new ArrayList<>();
        rolFrenteAPrestaciones = new ArrayList<>();
    }

    public Usuario(String usuario, String email, Contrasenia contrasenia) {
        this.nombreDeUsuario = usuario;
        this.email = email;
        this.contrasenia = contrasenia;

        horariosDisponibles = new ArrayList<>();
        roles = new ArrayList<>();
        entidadesDeInteres = new ArrayList<>();
        rolFrenteAPrestaciones = new ArrayList<>();

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

    public void aniadirRol(Rol rol) {

        roles.add(rol);
    }

    

}



