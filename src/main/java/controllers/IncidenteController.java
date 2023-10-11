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

    public IncidenteController(RepositorioIncidentes repositorioDeIncidentes) {
        this.repositorioIncidentes = repositorioDeIncidentes;
    }


    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioIncidentes.buscarIncidentesDeInteresPara(Long.parseLong(context.pathParam("id")));
        model.put("incidentes", incidentes);
        context.render("presentacion/menuPrincipal.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        /*
        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/cierreIncidentes.hbs", model);
        */
    }

    @Override
    public void save(Context context) {
        /*Incidente incidente = new Incidente();
        this.asignarParametros(incidente, context);
        this.repositorioDeIncidentes.guardar(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("/aperturaIncidentes");*/
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

    //DATOS PARA CREAR INCIDENTES

    /*
    PrestacionDeServicio servicioAfectado (nombre, establecimiento),
    Usuario usuarioReportador,
    Comunidad comunidadDondeSeReporta,
    String descripcion
    */

    /*
    private void asignarParametros(Incidente incidente, Context context) {
        if(context.formParam("servicioAfectado") != null ) {
            PrestacionDeServicio servicioAfectado = new PrestacionDeServicio();

            servicio.setNombre(context.formParam("nombre"));
        }
    }
    */
}
