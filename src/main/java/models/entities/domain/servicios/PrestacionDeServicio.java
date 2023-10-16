package models.entities.domain.servicios;

import models.repositories.datos.RepositorioPrestacionesDeServicio;
import models.entities.domain.Persistente;
import models.entities.domain.entidades.Establecimiento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name="establecimiento_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    public PrestacionDeServicio() {
    }

    public void habilitar(){ estaHabilitado = true;}

    public void deshabilitar(){ estaHabilitado = false; }

    public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento) {
        this.servicio = servicio;
        this.establecimiento = establecimiento;
        this. estaHabilitado = true;
       // new RepositorioPrestacionesDeServicio().agregarPrestacion(this);
    }

    public String obtenerTextoRelevante() {

        return "servicio: " + this.servicio.getDescripcion() + "en establecimiento: " + this.establecimiento.getNombre();
    }

}
