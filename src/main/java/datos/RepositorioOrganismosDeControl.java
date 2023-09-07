package datos;

import domain.entidades.Entidad;
import domain.entidades.OrganismoDeControl;
import domain.entidades.PrestadoraDeServicio;

import java.util.ArrayList;

public class RepositorioOrganismosDeControl {

    public ArrayList<OrganismoDeControl> organismosDeControl = new ArrayList<OrganismoDeControl>();
    public ArrayList<PrestadoraDeServicio> prestadorasDeServicio = new ArrayList<PrestadoraDeServicio>();
    public ArrayList<Entidad> entidades = new ArrayList<Entidad>();

    private static RepositorioOrganismosDeControl instancia = null;

    public static RepositorioOrganismosDeControl getInstance(){
        if (instancia == null) {instancia = new RepositorioOrganismosDeControl();}
        return instancia;
    }

    public void guardarOrganismoDeControl(OrganismoDeControl org){
        organismosDeControl.add(org);
    }
    public void guardarPrestadoraDeServicio(PrestadoraDeServicio prestadora){
        prestadorasDeServicio.add(prestadora);
    }
    public void guardarEntidad(Entidad entidad){
        entidades.add(entidad);
    }

}
