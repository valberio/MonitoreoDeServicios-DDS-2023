package controllers;

import models.repositories.datos.RepositorioEntidades;

public class EntidadController {
    private RepositorioEntidades repositorioEntidades;

    public EntidadController(RepositorioEntidades repositorioDeServicios) {
        this.repositorioEntidades = repositorioDeServicios;
    }
}
