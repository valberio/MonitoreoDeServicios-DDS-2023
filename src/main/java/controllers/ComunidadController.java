package controllers;

import models.repositories.datos.RepositorioComunidades;

public class ComunidadController {
    private RepositorioComunidades repositorioComunidades;

    public ComunidadController(RepositorioComunidades repositorioDeServicios) {
        this.repositorioComunidades = repositorioDeServicios;
    }
}
