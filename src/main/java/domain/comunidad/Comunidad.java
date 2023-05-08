package domain.comunidad;

import domain.registro.Usuario;
import domain.roles.Administrador;
import domain.roles.Miembro;
import domain.servicios.Servicio;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {
    private String nombre;
    private ArrayList<Miembro> miembros = new ArrayList<Miembro>();
    private ArrayList<Administrador> administradores = new ArrayList<Administrador>();
    private ArrayList<Servicio> serviciosDeInteres;

    public void definirServicio(String tipoDeServicio, String descripcion){
        Servicio servicioAAgregar = new Servicio(tipoDeServicio, descripcion);
    }

    public void modificarDescripcion(Servicio servicio, String nuevaDescripcion){
        servicio.modificarDescripcion(nuevaDescripcion);
    }

    public void darDeBaja(Servicio servicio){
        serviciosDeInteres.remove(servicio);
    }

    public void aniadirMiembro(Miembro miembro){
        this.miembros.add(miembro);
    }

    public void eliminarMiembro(Miembro miembro){
        this.miembros.remove(miembro);
    }
}
