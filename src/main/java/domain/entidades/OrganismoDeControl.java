package domain.entidades;

import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="organismo_de_control")
public class OrganismoDeControl extends Persistente {

    @Column(name="nombre")
    private String nombre;
    @Column(name="cuit")
    private String CUIT;
    @OneToMany(mappedBy = "organismoDeControl")
    private List<PrestadoraDeServicio> entidadesPrestadorasControladas;

    public OrganismoDeControl() {
        this.entidadesPrestadorasControladas = new ArrayList<>();
    }

    public void aniadirPrestadoraControlada(PrestadoraDeServicio prestadora) {this.entidadesPrestadorasControladas.add(prestadora);
        prestadora.setOrganismoDeControl(this);
    }

}
