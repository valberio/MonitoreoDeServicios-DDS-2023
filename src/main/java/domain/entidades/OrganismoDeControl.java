package domain.entidades;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OrganismoDeControl {
    private String nombre;

    private ArrayList<PrestadoraDeServicio> EntidadesPrestadorasControladas = new ArrayList<PrestadoraDeServicio>();

    public OrganismoDeControl(String nombre ) {
        this.nombre = nombre;
    }

    public void AnadirPrestadoraControlada(PrestadoraDeServicio prestadora) {EntidadesPrestadorasControladas.add(prestadora);}

}
