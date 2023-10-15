package controllers;


import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entities.domain.comunidad.Comunidad;
import models.entities.domain.incidentes.Estado;
import models.entities.domain.incidentes.EstadoIncidente;
import models.entities.domain.registro.Usuario;
import models.entities.domain.servicios.PrestacionDeServicio;
import models.repositories.datos.*;
import server.utils.ICrudViewsHandler;
import models.entities.domain.incidentes.Incidente;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class IncidenteController extends Controller implements ICrudViewsHandler {
    private RepositorioIncidentes repositorioIncidentes;
    private RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio;
    private RepositorioComunidades repositorioComunidades;

    private RepositorioUsuarios repositorioUsuarios;
    public IncidenteController(RepositorioIncidentes repositorioDeIncidentes, RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio, RepositorioComunidades repositorioComunidades, RepositorioUsuarios repositorioUsuarios) {
        this.repositorioIncidentes = repositorioDeIncidentes;
        this.repositorioPrestacionesDeServicio = repositorioPrestacionesDeServicio;
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioUsuarios = repositorioUsuarios;

    }


    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        String id = context.sessionAttribute("id").toString();
        List<Incidente> incidentes = this.repositorioIncidentes.buscarIncidentesDeInteresPara(Long.parseLong(id));
        model.put("incidentes", incidentes);
        context.render("presentacion/menuPrincipal.hbs", model);
    }

    //el show no lo implementamos pq ya con el index mostramos los detalles del incidente.
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

        Incidente incidente  = (Incidente) this.repositorioIncidentes.buscar(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/cierreIncidentes.hbs", model);

    }

    @Override
    public void update(Context context) {

        Incidente incidente  = (Incidente) this.repositorioIncidentes.buscar(Long.parseLong(context.pathParam("id")));

        Usuario usuario = (Usuario) this.repositorioUsuarios.buscar(Long.parseLong(context.sessionAttribute("id")));


        EstadoIncidente nuevoEstado = new EstadoIncidente(usuario, LocalDateTime.now(), incidente);

        nuevoEstado.setEstado(Estado.RESUELTO);

        repositorioIncidentes.actualizar(incidente);

    }

    @Override
    public void delete(Context context) {

    }


}
