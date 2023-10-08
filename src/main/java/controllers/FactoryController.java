package controllers;

import models.repositories.datos.RepositorioOrganismosDeControl;
import models.repositories.datos.RepositorioEntidades;
import models.repositories.datos.RepositorioComunidades;
import models.repositories.datos.RepositorioIncidentes;
import models.repositories.datos.RepositorioUsuarios;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Entidad": controller = new EntidadController(new RepositorioEntidades()); break;
            case "Comunidad": controller = new ComunidadController(new RepositorioComunidades()); break;
            case "Incidente": controller = new IncidenteController(new RepositorioIncidentes()); break;
            case "Usuario": controller = new UsuarioController(new RepositorioUsuarios()); break;
            case "OrganismoDeControl": controller = (new RepositorioOrganismosDeControl()); break;

        }
        return controller;
    }
}
