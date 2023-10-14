package models.entities.domain.roles;

import models.entities.domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "permiso")
public class Permiso extends Persistente {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombreInterno")
    private String nombreInterno;

    public boolean coincideConNombreInterno(String nombre) {
        return this.nombreInterno.equals(nombre);
    }
    @Column(name="descripcion")
    String descripcion;

}
