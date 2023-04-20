package domain.roles;

import domain.registro.Usuario;
import domain.servicios.Servicio;

public class Administrador extends Rol{

    public void darDeAlta(Servicio servicio){
        servicio.habilitar();
    }

    public void darDeBaja(Servicio servicio){
        servicio.deshabilitar();
    }

    public void darDeAlta(Usuario usuario){
        usuario.habilitar();
    }

    public void darDeBaja(Usuario usuario){
        usuario.deshabilitar();
    }
}
