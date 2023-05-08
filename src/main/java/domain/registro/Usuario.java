package domain.registro;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter

public class Usuario {

    private String usuario;
    private String apellido;
    private String email;
    private Contrasenia contrasenia;
    private Boolean bloqueado = false;

    public Usuario(String usuario, Contrasenia contrasenia, String email) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.bloqueado = false;
    }
}
