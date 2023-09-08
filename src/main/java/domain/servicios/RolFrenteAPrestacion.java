package domain.servicios;

import domain.Persistente;
import domain.registro.Identificador;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "rol_contra_prestacion")
@Entity
public class RolFrenteAPrestacion extends Persistente {

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    @ManyToOne
    private PrestacionDeServicio prestacionDeServicioInvolucrada;

    @Enumerated(EnumType.STRING)
    private Identificador rol;
}
