package domain.roles;

import domain.Persistente;
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
    @Column(name="descripcion")
    String descripcion;

}
