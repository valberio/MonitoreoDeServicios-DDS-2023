package domain.comunidad;

import domain.registro.Usuario;
import domain.roles.Administrador;
import domain.servicios.Servicio;

import java.util.List;

public class Comunidad {
    private String nombre;
    private List<Usuario> miembros;
    private List<Administrador> administradores;
    private List<Servicio> serviciosDeInteres;

    public void definirServicio(String tipoDeServicio, String descripcion){
        Servicio servicioAAgregar = new Servicio(tipoDeServicio, descripcion);
    }

    public void modificarDescripcion(Servicio servicio, String nuevaDescripcion){
        servicio.modificarDescripcion(nuevaDescripcion);
    }

    public void darDeBaja(Servicio servicio){
        //Tendría la misma función que el Administrador? Quiza la responsabilidad de darlo de baja en si es del admin y la comunidad se lo pide
        serviciosDeInteres.remove(servicio);
    }
}
