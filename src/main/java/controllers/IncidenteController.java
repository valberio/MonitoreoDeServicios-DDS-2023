package controllers;


import io.javalin.http.Context;
import models.repositories.datos.RepositorioIncidentes;
import server.utils.ICrudViewsHandler;
import models.entities.domain.incidentes.Incidente;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class IncidenteController implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;

    public IncidenteController(RepositorioIncidentes repositorioDeServicios) {
        this.repositorioIncidentes = repositorioDeServicios;
    }


    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioIncidentes.buscarIncidentesDeInteresPara(Long.parseLong(context.queryParam("id")));
        model.put("incidentes", incidentes);
        context.render("presentacion/menuPrincipal.hbs", model);
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
