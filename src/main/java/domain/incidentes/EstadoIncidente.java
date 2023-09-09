package domain.incidentes;

import domain.Persistente;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.time.LocalDateTime;
@Getter
@Setter
@Table(name="estado_incidente")
@Entity
//Esto se tiene que persistir para tener trazabilidad en los estados de los incidentes
public class EstadoIncidente extends Persistente {
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuarioResponsable;
    @Column(name="fecha_modificacion")
    private LocalDateTime fechaModificacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="incidente_id", referencedColumnName = "id")
    private Incidente incidente;

}