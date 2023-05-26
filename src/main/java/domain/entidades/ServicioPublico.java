package domain.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioPublico {
    private Entidad entidad;
    private Boolean estaHabilitado = true;

    public ServicioPublico(Entidad entidad){
        this.entidad = entidad;
    }


}
