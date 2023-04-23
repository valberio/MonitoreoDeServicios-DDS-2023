package domain.registro;

import lombok.Getter;
import domain.roles.Rol;
import domain.roles.Administrador;
import domain.roles.Miembro;
import domain.comunidad.Comunidad;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter

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

    public void aniadirRol(Comunidad comunidad){
        Rol nuevoRol = new Miembro(comunidad);
        this.roles.add(nuevoRol);
    }

    public void removerRol(Comunidad comunidad){

        this.roles.remove(roles.stream().filter(unRol-> unRol.getComunidad().equals(comunidad)).findFirst());
    }
}
