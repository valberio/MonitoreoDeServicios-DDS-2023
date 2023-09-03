package domain.entidades;
import domain.Persistente;
import domain.entidades.Entidad;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name="prestadora_de_servicio")
public class PrestadoraDeServicio extends Persistente {

    @Column(name="nombre")
    private String nombre;
    @OneToMany(mappedBy="prestadora_id")
    private ArrayList<Entidad> entidades;
    @OneToOne
    private Usuario usuarioDesignado;

    public PrestadoraDeServicio(){ this.entidades = new ArrayList<>(); }

    public void aniadirEntidad(Entidad entidad){
        entidades.add(entidad);
        entidad.setPrestadora(this);}
    }
