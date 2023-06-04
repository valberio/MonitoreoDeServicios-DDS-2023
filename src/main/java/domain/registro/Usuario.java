package domain.registro;

import domain.Localizacion.Localizacion;
import domain.roles.Rol;
import domain.servicios.Servicio;
import domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
@Setter

public class Usuario {

    private String usuario;
    private String apellido;
    private String email;
    private Contrasenia contrasenia;
    private Boolean bloqueado = false;

    private Localizacion localizacion;

    ArrayList<Rol> roles = new ArrayList<>();
    ArrayList<Entidad> entidadesDeInteres = new ArrayList<Entidad>();

    public Usuario(String usuario, Contrasenia contrasenia, String email) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.bloqueado = false;
    }

    public ArrayList<Servicio> serviciosDeInteres(){

        return entidadesDeInteres.stream().flatMap(entidad->entidad.serviciosConIncidente()).collect(Collectors.toCollection(ArrayList::new));
    }

    public String getLocalizacion() throws IOException {

        return localizacion.obtenerse();
    }
}
