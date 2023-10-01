package models.entities.domain.roles;

import models.entities.domain.Persistente;
import models.entities.domain.comunidad.Comunidad;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol extends Persistente {
    @Column(name="nombre")
    private String nombre;

    @OneToOne
    private Comunidad comunidad;

    @ManyToMany
    private List<Permiso> permisos;

    boolean tenesPermiso(Permiso unPermiso) {

        return permisos.contains(unPermiso);

    }

    public Rol() {

        permisos = new ArrayList<>();
    }
}
