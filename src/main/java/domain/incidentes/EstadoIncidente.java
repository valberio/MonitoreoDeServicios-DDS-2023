package domain.incidentes;

import domain.Persistente;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Getter
@Setter
@Table(name="estado_incidente")
@Entity
//Esto se tiene que persistir para tener trazabilidad en los estados de los incidentes
public class EstadoIncidente extends Persistente {
    Usuario usuarioResponsable;
    LocalDateTime fechaModificacion;
    Estado estado;
    Incidente incidente;

}