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

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Incidente {

    public PrestacionDeServicio servicioAfectado;
    public Usuario usuarioReportador;
    public LocalDateTime fechaReporte;
    public LocalDateTime fechaResolucion;
    public EstadoIncidente estado = EstadoIncidente.ACTIVO;
    public Comunidad comunidadDondeSeReporta;
    Notificador notificador = Notificador.getInstancia();
    String descripcion;

    public Incidente(PrestacionDeServicio prestacionDeServicio, Usuario usuarioReportador, LocalDateTime fechaReporte, Comunidad comunidad, String observacion) {
        this.servicioAfectado = prestacionDeServicio;
        this.usuarioReportador = usuarioReportador;
        this.fechaReporte = fechaReporte;
        this.comunidadDondeSeReporta = comunidad;
        this.descripcion = observacion;

        RepositorioIncidentes archivo = RepositorioIncidentes.getInstance();
        archivo.guardarIncidente(this);

        this.servicioAfectado.setEstaHabilitado(false);

    }

    public ArrayList<Usuario> obtenerUsuariosInteresados() {

        ArrayList<Usuario> usuariosRegistrados = RepositorioUsuarios.getUsuariosRegistrados();

        List<Usuario> usuariosInteresadosEnServicio = usuariosRegistrados.stream().filter(usuario->usuario.teInteresa(servicioAfectado)).collect(Collectors.toList());

        List<Usuario> interesadosPorComunidadEnComun = usuariosRegistrados.stream().filter(usuario->usuario.estasEn(comunidadDondeSeReporta)).toList(); //le saco la lista de roles al usuario y la comunidad de esa lista de roles. Me fijo si es compatible la comunidad con la comunidad del usuario que reportó

        ArrayList<Usuario> usuariosInteresados = new ArrayList<>(usuariosInteresadosEnServicio);

        usuariosInteresados.addAll(interesadosPorComunidadEnComun);


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

    public void cerrarse()
    {
        this.fechaResolucion = LocalDateTime.now();
        this.setEstado(EstadoIncidente.RESUELTO);
        notificador.cerreUnIncidente(this);
    }

}
