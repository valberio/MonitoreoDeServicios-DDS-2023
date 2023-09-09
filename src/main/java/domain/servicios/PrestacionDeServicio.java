package domain.servicios;

import datos.RepositorioPrestacionesDeServicio;
import domain.Persistente;
import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.nio.file.attribute.FileStoreAttributeView;

@Getter
@Setter
@Entity
@Table(name="prestacion_de_servicio")
public class PrestacionDeServicio extends Persistente {

    @ManyToOne(cascade = CascadeType.PERSIST, fetch= FetchType.LAZY)
    private Servicio servicio;
    @Column(name="estaHabilitado")
    private Boolean estaHabilitado;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="prestacionDeServicio_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    public PrestacionDeServicio() {
    }

    public void habilitar(){ estaHabilitado = true;}

    public void deshabilitar(){ estaHabilitado = false; }

    public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento) {
        this.servicio = servicio;
        this.establecimiento = establecimiento;
        estaHabilitado = true;
        new RepositorioPrestacionesDeServicio().agregarPrestacion(this);
    }

    public String obtenerTextoRelevante() {

        return "servicio: " + this.servicio.getDescripcion() + "en establecimiento: " + this.establecimiento.getNombre();
    }

    public Entidad obtenerEntidadAfectada(){
        return establecimiento.getEntidad();
    }
}
