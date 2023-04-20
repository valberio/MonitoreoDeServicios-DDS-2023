package domain.roles;

import domain.comunidad.Comunidad;

public class Miembro extends Rol {
    public Miembro(Comunidad comunidad) {
        this.comunidad = comunidad;
    }
}
