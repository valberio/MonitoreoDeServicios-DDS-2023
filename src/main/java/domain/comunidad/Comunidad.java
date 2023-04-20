package domain.comunidad;

import domain.registro.Usuario;
import domain.roles.Administrador;
import domain.servicios.Servicio;

import java.util.List;

public class Comunidad {
    protected String nombre;

    public List<Usuario> miembros;
    public List<Administrador> administradores;
    public List<Servicio> serviciosDeIntereses;

    void definirComunidad(String servicio, String otroServicio){
        //TODO

    }

    void modificarDescripcion(Servicio servicio){
        //TODO
    }

    void darDeBaja(Servicio servicio){
        //TODO
        //Tendría la misma función que el Administrador?
    }
}
