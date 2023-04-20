package domain.registro;

import lombok.Getter;
import domain.roles.Rol;

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

    public void habilitar(){ bloqueado = false;}
    public void deshabilitar(){ bloqueado = true; }
}
