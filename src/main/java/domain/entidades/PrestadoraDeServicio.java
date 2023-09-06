package domain.entidades;
import domain.Persistente;
import domain.entidades.Entidad;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="prestadora_de_servicio")
public class PrestadoraDeServicio extends Persistente {
    @Column(name="nombre")
    private String nombre;
    @OneToMany(mappedBy="prestadora")
    private List<Entidad> entidades;
    @OneToOne
    private Usuario usuarioDesignado;

    @ManyToOne
    @JoinColumn(name="organismoDeControl_id", referencedColumnName = "id")
    private OrganismoDeControl organismoDeControl;

    public PrestadoraDeServicio(){ this.entidades = new ArrayList<>(); }

    public void aniadirEntidad(Entidad entidad){
        entidades.add(entidad);
        entidad.setPrestadora(this);}
    }
