package domain.comunidad;

import domain.registro.Usuario;
import domain.servicios.Servicio;

import java.util.ArrayList;

public class Comunidad {
    private String nombre;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Servicio> serviciosDeInteres = new ArrayList<>();

    public void definirServicio(String tipoDeServicio, String descripcion){
        Servicio servicioAAgregar = new Servicio(tipoDeServicio, descripcion);
    }

    public void modificarDescripcion(Servicio servicio, String nuevaDescripcion){
        servicio.modificarDescripcion(nuevaDescripcion);
    }

    public void darDeBaja(Servicio servicio){
        serviciosDeInteres.remove(servicio);
    }

    /*
    public void aniadirMiembro(Miembro miembro){
        this.miembros.add(miembro);
    }

    public void eliminarMiembro(Miembro miembro){
        this.miembros.remove(miembro);
    }
    */
}
