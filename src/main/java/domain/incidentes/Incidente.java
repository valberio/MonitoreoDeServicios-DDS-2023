package domain.incidentes;

import datos.RepositorioIncidentes;
import datos.RepositorioUsuarios;
import domain.Persistente;
import domain.comunidad.Comunidad;
import domain.entidades.Entidad;
import domain.notificaciones.Notificador;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name="incidente")
public class Incidente extends Persistente {

    @ManyToOne
    private PrestacionDeServicio servicioAfectado;
    @ManyToOne
    private Usuario usuarioReportador;
    //Debatible
    @Column(name="fecha_reporte")
    private LocalDateTime fechaReporte;
    @Column(name="fecha_resolucion")
    private LocalDateTime fechaResolucion;
    //Las fechas de reporte y resolucion estan en los estadoIncidente, dejarlos acá sería
    //repetir datos y no cumplir las reglas de la normalización. Peero a nivel objeto, los
    //incidentes no tienen una lista de estados, tienen sólo el estado actual. Para pensar gente...
    @OneToOne
    private EstadoIncidente estado;
    @OneToMany(mappedBy = "incidente")
    private List<EstadoIncidente> estadosDeIncidentes;
    @ManyToOne
    @JoinColumn(name = "incidente_id", referencedColumnName = "id")
    private Comunidad comunidadDondeSeReporta;
    @Transient
    private Notificador notificador = Notificador.getInstancia();
    @Column
    private String descripcion;

    public Incidente(PrestacionDeServicio servicioAfectado, Usuario usuarioReportador, Comunidad comunidadDondeSeReporta, String descripcion) {
        this.setUsuarioReportador(usuarioReportador);
        this.setServicioAfectado(servicioAfectado);
        this.setComunidadDondeSeReporta(comunidadDondeSeReporta);
        this.setFechaReporte(LocalDateTime.now());
        this.setDescripcion(descripcion);
        this.servicioAfectado.setEstaHabilitado(false);

        RepositorioIncidentes archivo = RepositorioIncidentes.getInstance();
        archivo.guardarIncidente(this);

        this.servicioAfectado.setEstaHabilitado(false);

        this.estado = new EstadoIncidente();

        this.estadosDeIncidentes = new ArrayList<>();

    }

    public Incidente() {

    }

    public ArrayList<Usuario> obtenerUsuariosInteresados() {

        ArrayList<Usuario> usuariosRegistrados = RepositorioUsuarios.getUsuariosRegistrados();

        ArrayList<Usuario> usuariosInteresados = (ArrayList<Usuario>) usuariosRegistrados.stream().filter(usuario->usuario.teInteresa(this)).collect(Collectors.toList());

        if(usuariosInteresados.contains(usuarioReportador)) {
            usuariosInteresados.remove(usuarioReportador);
        }

        return this.obtenerUsuariosInteresadosSinRepetir(usuariosInteresados);
    }

    public ArrayList<Usuario> obtenerUsuariosInteresadosSinRepetir(ArrayList<Usuario> usuarios) {

        HashSet<Usuario> conjunto = new HashSet<>();
        ArrayList<Usuario>usuariosDuplicados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (!conjunto.add(usuario)) {
                usuariosDuplicados.add(usuario);
            }
        }

        usuarios.removeAll(usuariosDuplicados);

        return usuarios;
    }

    public String getLocalizacion() {

        return this.servicioAfectado.getEstablecimiento().getUbicacionGeografica().getMunicipio().getNombre();
    }

    public Entidad obtenerEntidad(){
        return this.servicioAfectado.getEstablecimiento().getEntidad();
    }

    public Long   obtenerTiempoDeCierre(){
        Duration duracion = Duration.between(this.getFechaReporte(), this.getFechaResolucion());
        return (duracion.toMinutes());
       // return (Long) ChronoUnit.MICROS.between(this.getFechaResolucion(), this.getFechaReporte());
    }

    public Long obtenerDiferenciaDeRegistroEntre(Incidente incidente2){
        Duration duracion = Duration.between(this.getFechaReporte(), incidente2.getFechaReporte());
        return (duracion.toMinutes());
        //return (Long) ChronoUnit.MICROS.between(this.getFechaReporte(), incidente2.getFechaReporte());
    }

    public void cerrarse(Usuario usuarioResponsable) throws MessagingException {
        this.fechaResolucion = LocalDateTime.now();
        estadosDeIncidentes.add(this.estado); //guardo el anterior antes de actualizarlo
        EstadoIncidente estadoResuelto = new EstadoIncidente();
        estadoResuelto.setUsuarioResponsable(usuarioResponsable);
        estadoResuelto.setFechaModificacion(LocalDateTime.now());
        estadoResuelto.setEstado(Estado.RESUELTO);
        estadoResuelto.setIncidente(this);
        this.setEstado(estadoResuelto);
        notificador.cerreUnIncidente(this);
        this.servicioAfectado.setEstaHabilitado(true);
    }

}
