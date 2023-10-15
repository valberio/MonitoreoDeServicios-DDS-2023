package controllers;

import models.entities.domain.servicios.PrestacionDeServicio;
import models.repositories.datos.*;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Entidad": controller = new EntidadController(new RepositorioEntidades()); break;
            case "Comunidad": controller = new ComunidadController(new RepositorioComunidades()); break;
            case "Incidente": controller = new IncidenteController(new RepositorioIncidentes(), new RepositorioPrestacionesDeServicio(), new RepositorioComunidades(), new RepositorioUsuarios()); break;
            case "Usuario": controller = new UsuarioController(new RepositorioUsuarios(), new RepositorioEntidades()); break;
            case "OrganismoDeControl": controller = (new RepositorioOrganismosDeControl()); break;
            case "PrestacionDeServicio" : controller = (new PrestacionDeServicioController(new RepositorioPrestacionesDeServicio())); break;
            case "RolPermiso": controller = new RolPermisoController(new RepositorioDePermisos(), new RepositorioUsuarios(), new RepositorioDeRoles());
            case "Ranking": controller = new RankingController();
            case "CargaOrganismosYEntidadesController" : controller = (new CargaOrganismosYEntidadesController()); break;
        }
        return controller;
    }
}
