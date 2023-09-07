package domain.incidentes;

import domain.Persistente;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Table(name="estado_incidente")
@Entity
//Esto se tiene que persistir para tener trazabilidad en los estados de los incidentes
public class EstadoIncidente extends Persistente {
    @OneToOne
    private Usuario usuarioResponsable;
    private LocalDateTime fechaModificacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name="incidente_id", referencedColumnName = "id")
    Incidente incidente;

}