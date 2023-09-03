package domain.incidentes;

import datos.RepositorioIncidentes;
import datos.RepositorioUsuarios;
import domain.comunidad.Comunidad;
import domain.entidades.Entidad;
import domain.notificaciones.Notificador;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name="incidente")
public class Incidente {

    public PrestacionDeServicio servicioAfectado;
    public Usuario usuarioReportador;
    public LocalDateTime fechaReporte;
    public LocalDateTime fechaResolucion;
    public EstadoIncidente estado = EstadoIncidente.ACTIVO;
    public Comunidad comunidadDondeSeReporta;
    Notificador notificador = Notificador.getInstancia();
    String descripcion;

    public Incidente() {

        RepositorioIncidentes archivo = RepositorioIncidentes.getInstance();
        archivo.guardarIncidente(this);

        this.servicioAfectado.setEstaHabilitado(false);

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

    public Long obtenerTiempoDeCierre(){
        Duration duracion = Duration.between(this.getFechaReporte(), this.getFechaResolucion());
        return (duracion.toHours() % 24);
    }

    public Long obtenerDiferenciaDeRegistroEntre(Incidente incidente2){
        Duration duracion = Duration.between(this.getFechaReporte(), incidente2.getFechaReporte());
        return (duracion.toHours() % 24);

    }

    public void cerrarse() throws MessagingException {
        this.fechaResolucion = LocalDateTime.now();
        this.setEstado(EstadoIncidente.RESUELTO);
        notificador.cerreUnIncidente(this);
    }

}
