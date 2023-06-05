package domain.registro;

import domain.Localizacion.Localizacion;
import domain.roles.Rol;
import domain.servicios.Servicio;
import domain.entidades.Entidad;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class Usuario {
    private String usuario;
    private String apellido;
    private String email;
    private Contrasenia contrasenia;
    private Boolean bloqueado;
    private Localizacion localizacion;
    ArrayList<Rol> roles = new ArrayList<>();
    ArrayList<Entidad> entidadesDeInteres = new ArrayList<>();

    public Usuario(String usuario, Contrasenia contrasenia, String email) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.bloqueado = false;
    }

    public void agregarEntidadesDeInteres (Entidad ... entidad) {

        entidadesDeInteres.addAll(List.of(entidad));
    }

    public ArrayList<Servicio> serviciosDeInteres(){

        return entidadesDeInteres.stream().flatMap(entidad->entidad.serviciosConIncidente()).collect(Collectors.toCollection(ArrayList::new));
    }

}
