package domain.comunidad;

import domain.Persistente;
import domain.incidentes.Incidente;
import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="comunidad")
public class Comunidad extends Persistente {

    @Column(name="nombre")
    private String nombre;
    @ManyToMany
    private List<Usuario> usuarios;
    @ManyToMany
    private List<Servicio> serviciosDeInteres;
    @OneToMany
    private List<Incidente> incidentesReportados;

    public Comunidad() {
       usuarios = new ArrayList<>();
       serviciosDeInteres = new ArrayList<>();
       incidentesReportados = new ArrayList<>();
    }

    public void agregarServiciosDeInteres(Servicio ... servicio) { this.serviciosDeInteres.addAll(List.of(servicio));}

    public void darDeBaja(Servicio servicio){
        serviciosDeInteres.remove(servicio);
    }

    public void seInformoUnIncidente(Incidente unIncidente){
        this.incidentesReportados.add(unIncidente);
    }

    public void agregarUsuarios(Usuario ... usuario) {  this.usuarios.addAll(List.of(usuario));
    }
}
