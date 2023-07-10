package domain.roles;

import domain.comunidad.Comunidad;
import domain.incidentes.Incidente;
import domain.notificaciones.Notificador;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Rol {

    String nombre;

    Comunidad comunidad;

    ArrayList<Permiso> permisos = new ArrayList<>();

    boolean tenesPermiso(Permiso unPermiso) {

        return permisos.contains(unPermiso);

    }

}
