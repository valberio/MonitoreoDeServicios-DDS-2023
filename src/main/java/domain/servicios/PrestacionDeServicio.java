package domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestacionDeServicio {

    private Servicio servicio;
    private Boolean estaHabilitado;

    public void habilitar(){ estaHabilitado = true;}

    public void deshabilitar(){ estaHabilitado = false; }

    public PrestacionDeServicio(Servicio servicio) {
        this.servicio = servicio;
        estaHabilitado = true;
    }
}
