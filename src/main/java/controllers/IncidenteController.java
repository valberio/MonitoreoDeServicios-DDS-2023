package controllers;


import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entities.domain.comunidad.Comunidad;
import models.entities.domain.registro.Usuario;
import models.entities.domain.servicios.PrestacionDeServicio;
import models.repositories.datos.RepositorioComunidades;
import models.repositories.datos.RepositorioIncidentes;
import models.repositories.datos.RepositorioPrestacionesDeServicio;
import server.utils.ICrudViewsHandler;
import models.entities.domain.incidentes.Incidente;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class IncidenteController extends Controller implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio;
    private RepositorioComunidades repositorioComunidades;
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

        context.render("incidentes/aperturaIncidentes.hbs");

    }

    @Override
    public void save(Context context) {
        Usuario usuarioLogueado =  super.usuarioLogueado(context);
        PrestacionDeServicio servicioAfectado = null;
        Comunidad comunidad = null;
        String descripcion = null;

        if (context.formParam("servicio") != null) {
            Long servicioAfectadoId = (Long) this.repositorioPrestacionesDeServicio.obtenerIdDelServicioPorNombre(context.formParam("servicio"));
            servicioAfectado = this.repositorioPrestacionesDeServicio.obtenerPrestacion(servicioAfectadoId);
        }

        if (context.formParam("comunidad") != null) {
            Long comunidadId = (Long) this.repositorioComunidades.obtenerIdDeComunidadPorNombre(context.formParam("comunidad"));
            comunidad = this.repositorioComunidades.obtenerComunidad(comunidadId);
        }

        if (context.formParam("observaciones") != null) {
            descripcion = context.formParam("observaciones");
        }

        Incidente incidente = new Incidente(servicioAfectado, usuarioLogueado, comunidad, descripcion);
        //this.asignarParametros(incidente, context); lo vole porque el contructor de incidente tenia mas complejidad kjj
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


}
