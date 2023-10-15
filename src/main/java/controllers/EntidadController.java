package controllers;

import io.javalin.http.Context;
import models.entities.domain.entidades.Entidad;
import models.entities.domain.incidentes.Incidente;
import models.repositories.datos.RepositorioEntidades;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadController implements ICrudViewsHandler {
    private RepositorioEntidades repositorioEntidades;

    public EntidadController(RepositorioEntidades repositorioDeServicios) {
        this.repositorioEntidades = repositorioDeServicios;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<String> entidades = this.repositorioEntidades.buscarTodosLosNombresDeEntidades();
        model.put("entidades", entidades);
        context.render("index/registroEntidadesDeInteres.hbs", model);
    }

    @Override
    public void show(Context context) {
        //TODO encajarle todas las entidades

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
