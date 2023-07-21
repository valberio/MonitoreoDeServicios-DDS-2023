package domain.comunidad;

import domain.incidentes.Incidente;
import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class Comunidad {
    private String nombre;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Servicio> serviciosDeInteres = new ArrayList<>();

    private ArrayList<Incidente> incidentesReportados = new ArrayList<>();

    public void definirServicio(String tipoDeServicio, String descripcion){
        Servicio servicioAAgregar = new Servicio(tipoDeServicio, descripcion);
    }

    public void modificarDescripcion(Servicio servicio, String nuevaDescripcion){
        servicio.modificarDescripcion(nuevaDescripcion);
    }

    public void darDeBaja(Servicio servicio){
        serviciosDeInteres.remove(servicio);
    }

    public void seInformoUnIncidente(Incidente unIncidente){
        this.incidentesReportados.add(unIncidente);
    }

    public void agregarUsuario(Usuario ... usuario) {  this.usuarios.addAll(List.of(usuario));
    }


}
