package controllers;

import io.javalin.http.Context;
import models.entities.domain.incidentes.Incidente;
import models.repositories.datos.RepositorioComunidades;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadController implements ICrudViewsHandler {
    private RepositorioComunidades repositorioComunidades;

    public ComunidadController(RepositorioComunidades repositorioDeServicios) {
        this.repositorioComunidades = repositorioDeServicios;
    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
