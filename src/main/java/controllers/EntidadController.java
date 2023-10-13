package controllers;

import io.javalin.http.Context;
import models.repositories.datos.RepositorioEntidades;
import server.utils.ICrudViewsHandler;

public class EntidadController implements ICrudViewsHandler {
    private RepositorioEntidades repositorioEntidades;

    public EntidadController(RepositorioEntidades repositorioDeServicios) {
        this.repositorioEntidades = repositorioDeServicios;
    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {
        //TODO encajarle todas las entidades
        context.render("index/registroEntidadesDeInteres.hbs");
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
