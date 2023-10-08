package controllers;

import models.repositories.datos.RepositorioIncidentes;
import models.repositories.datos.RepositorioOrganismosDeControl;

public class OrganismoDeControlController {
    private RepositorioOrganismosDeControl repositorioOrganismosDeControl;

    public OrganismoDeControlController(RepositorioOrganismosDeControl repositorioDeServicios) {
        this.repositorioOrganismosDeControl = repositorioDeServicios;
    }
}
