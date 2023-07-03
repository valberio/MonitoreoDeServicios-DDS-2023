package domain.servicios;

import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.attribute.FileStoreAttributeView;

@Getter
@Setter
public class PrestacionDeServicio {

    private Servicio servicio;
    private Boolean estaHabilitado;
    private Establecimiento establecimiento;
    public void habilitar(){ estaHabilitado = true;}

    public void deshabilitar(){ estaHabilitado = false; }

    public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento) {
        this.servicio = servicio;
        this.establecimiento = establecimiento;
        estaHabilitado = true;
    }

    public String obtenerTextoRelevante() {

        return "servicio: " + this.servicio.getDescripcion() + "en establecimiento: " + this.establecimiento.getNombre();
    }

    public Entidad obtenerEntidadAfectada(){
        return establecimiento.getEntidad();
    }
}
