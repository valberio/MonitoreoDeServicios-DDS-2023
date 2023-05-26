package domain.roles;

import domain.comunidad.Comunidad;

import java.util.ArrayList;

public class Rol {

    String nombre;

    Comunidad comunidad;

    ArrayList<Permiso> permisos = new ArrayList<>();

    boolean tenesPermiso(Permiso unPermiso) {

        return permisos.contains(unPermiso);

    }

}
