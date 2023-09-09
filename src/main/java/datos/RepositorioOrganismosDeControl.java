package datos;

import domain.entidades.Entidad;
import domain.entidades.OrganismoDeControl;
import domain.entidades.PrestadoraDeServicio;
import domain.registro.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganismosDeControl implements WithSimplePersistenceUnit {

    private static RepositorioOrganismosDeControl instancia = null;

    public static RepositorioOrganismosDeControl getInstance(){
        if (instancia == null) {instancia = new RepositorioOrganismosDeControl();}
        return instancia;
    }

    public void agregarOrganismoDeControl(OrganismoDeControl org){
        entityManager().persist(org);
    }

    public boolean estaRegistrado(String cuit) {

        List<OrganismoDeControl> organismosDeControl = entityManager().createQuery("from OrganismoDeControl where CUIT = :c")
                .setParameter("c", cuit).getResultList();

        return !organismosDeControl.isEmpty();
    }

}
