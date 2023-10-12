package controllers;


import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.repositories.datos.RepositorioIncidentes;
import models.repositories.datos.RepositorioPrestacionesDeServicio;
import server.utils.ICrudViewsHandler;
import models.entities.domain.incidentes.Incidente;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class IncidenteController implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio;
    public IncidenteController(RepositorioIncidentes repositorioDeIncidentes) {
        this.repositorioIncidentes = repositorioDeIncidentes;
    }


    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        String id = context.sessionAttribute("id").toString();
        List<Incidente> incidentes = this.repositorioIncidentes.buscarIncidentesDeInteresPara(Long.parseLong(id));
        model.put("incidentes", incidentes);
        context.render("presentacion/menuPrincipal.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
       // context.result("Hola");
        context.render("incidentes/aperturaIncidentes.hbs");

    }

    @Override
    public void save(Context context) {
        Incidente incidente = new Incidente();
        this.asignarParametros(incidente, context);
        this.repositorioIncidentes.agregarIncidente(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("incidentes/aperturaIncidentes");
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


    private void asignarParametros(Incidente incidente, Context context) {
       /*
       if(context.formParam("servicio") != null )
            PrestacionDeServicio servicioAfectado = (PrestacionDeServicio) this.repositorioDePrestacionesDeServicio.buscar(context.formParam("servicio"));
            servicio.setServicioAfectado(servicioAfectado);

        if(context.formParam("comunidad") != null)
            Comunidad comunidad = (Comunidad) this.repositorioDeComunidad.buscar(context.formParam("comunidad"));
            servicio.setComunidad(comunidad);
        if(context.formParam("ubicacion") != null)
            servicio.setUbicacion(context.formParam("ubicacion"));


        if(context.formParam("observaciones") != null)
            servicio.setDescripcion(context.formParam("observaciones"));
        */
    }
}
