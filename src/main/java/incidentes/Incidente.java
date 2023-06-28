package incidentes;

import domain.comunidad.Comunidad;
import domain.notificaciones.Notificador;
import domain.registro.Registro;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Incidente {
    PrestacionDeServicio servicioAfectado;

    Usuario usuarioQueReporta;

    Comunidad comunidadDondeSeReporta;

    String descripcion;

    LocalDateTime fechaYHora;

    Notificador notificador = Notificador.getInstancia();

    public void reportar() {

        Notificador.notificar(this);

    }

    public ArrayList<Usuario> obtenerUsuariosInteresados() {

        ArrayList<Usuario> usuariosRegistrados = Registro.getInstancia().getUsuariosRegistrados();

        ArrayList<Usuario> usuariosInteresadosEnServicio = (ArrayList<Usuario>) usuariosRegistrados.stream().filter(usuario->usuario.teInteresa(servicioAfectado)).toList();

        ArrayList<Usuario> interesadosPorComunidadEnComun = (ArrayList<Usuario>) usuariosRegistrados.stream().filter(usuario->usuario.estasEn(comunidadDondeSeReporta)).toList(); //le saco la lista de roles al usuario y la comunidad de esa lista de roles. Me fijo si es compatible la comunidad con la comunidad del usuario que reportó

        ArrayList<Usuario> usuariosInteresados = new ArrayList<>(usuariosInteresadosEnServicio);

        usuariosInteresados.addAll(interesadosPorComunidadEnComun);

        if(usuariosInteresados.contains(usuarioQueReporta)) {
            usuariosInteresados.remove(usuarioQueReporta);
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

}
