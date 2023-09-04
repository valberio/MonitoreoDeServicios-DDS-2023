package domain.incidentes;

import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
//Esto se tiene que persistir para tener trazabilidad en los estados de los incidentes
public class EstadoIncidente {
    Usuario usuarioResponsable;
    LocalDateTime fechaModificacion;
    Estado estado;
    Incidente incidente;

}