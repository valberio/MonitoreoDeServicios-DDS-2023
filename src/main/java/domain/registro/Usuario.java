package domain.registro;

import lombok.Getter;
import domain.roles.Rol;
import domain.roles.Administrador;
import domain.roles.Miembro;
import domain.comunidad.Comunidad;

import java.util.ArrayList;

@Getter

public class Usuario {

    private String usuario;
    private String apellido;
    private ArrayList<Rol> roles;
    private String email;
    private Contrasenia contrasenia;
    private Boolean bloqueado = false;

    public Usuario(String usuario, Contrasenia contrasenia, String email) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.bloqueado = false;
    }

    public void añadirRol(Comunidad comunidad){
        Rol nuevoRol = new Miembro(comunidad);
        roles.add(nuevoRol);
    }

    public void removerRol(Comunidad comunidad){
            roles.remove(roles.stream().findFirst())
    }
}
