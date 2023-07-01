package domain.incidentes;

import datos.ArchivoIncidentes;
import domain.comunidad.Comunidad;
import domain.notificaciones.Notificador;
import domain.registro.Registro;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
public class Incidente {

    public PrestacionDeServicio servicioAfectado;
    public Usuario usuarioReportador;
    public LocalDateTime fechaReporte;
    public LocalDateTime fechaResolucion;

    public Comunidad comunidadDondeSeReporta;

    Notificador notificador = Notificador.getInstancia();
    String descripcion;

    public Incidente(PrestacionDeServicio prestacionDeServicio, Usuario usuarioReportador, LocalDateTime fechaReporte) {
        this.servicioAfectado = prestacionDeServicio;
        this.usuarioReportador = usuarioReportador;
        this.fechaReporte = fechaReporte;

        ArchivoIncidentes archivo = ArchivoIncidentes.getInstance();
        archivo.guardarIncidente(this);
        // cuando se crea un incidente deberiamos deshabilitar la prestacion de servicio? o de eso se va a
        // encargar un usuario superior?

    }


    public void reportar() {

        Notificador.notificar(this);

    }

    public ArrayList<Usuario> obtenerUsuariosInteresados() {

        ArrayList<Usuario> usuariosRegistrados = Registro.getInstancia().getUsuariosRegistrados();

        ArrayList<Usuario> usuariosInteresadosEnServicio = (ArrayList<Usuario>) usuariosRegistrados.stream().filter(usuario->usuario.teInteresa(servicioAfectado)).toList();

        ArrayList<Usuario> interesadosPorComunidadEnComun = (ArrayList<Usuario>) usuariosRegistrados.stream().filter(usuario->usuario.estasEn(comunidadDondeSeReporta)).toList(); //le saco la lista de roles al usuario y la comunidad de esa lista de roles. Me fijo si es compatible la comunidad con la comunidad del usuario que reportó

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

        return this.servicioAfectado.getEstablecimiento().getUbicacionGeografica().nombre;
    }

}
