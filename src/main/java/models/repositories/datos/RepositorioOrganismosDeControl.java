package models.repositories.datos;

import models.entities.domain.entidades.OrganismoDeControl;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

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

    public void eliminarOrganismoDeControl(OrganismoDeControl organismosDeControl) {
            entityManager().remove(organismosDeControl);
        }

        public OrganismoDeControl obtenerOrganismoDeControl(OrganismoDeControl organismoDeControl) {
            return find(OrganismoDeControl.class, organismoDeControl.getId());
        }

}
