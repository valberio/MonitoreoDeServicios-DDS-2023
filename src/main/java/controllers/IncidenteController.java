package controllers;


import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entities.domain.comunidad.Comunidad;
import models.entities.domain.incidentes.Estado;
import models.entities.domain.incidentes.EstadoIncidente;
import models.entities.domain.registro.Usuario;
import models.entities.domain.servicios.PrestacionDeServicio;
import models.repositories.datos.*;
import org.checkerframework.checker.units.qual.A;
import server.exceptions.AccessDeniedException;
import server.utils.ICrudViewsHandler;
import models.entities.domain.incidentes.Incidente;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        //context.result("Hola");
        context.render("presentacion/menuPrincipal.hbs", model);
    }

    //el show no lo implementamos pq ya con el index mostramos los detalles del incidente.
    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        String id = context.sessionAttribute("id").toString();
        List<Long> idComunidades = this.repositorioComunidades.buscarComunidadesDe(Long.parseLong(id));
        List<Comunidad> comunidades = new ArrayList<>();
        List<PrestacionDeServicio> prestacionesDeServicios = new ArrayList<>();

        for(Long idComunidad: idComunidades) {
            comunidades.add(this.repositorioComunidades.obtenerComunidad(idComunidad));
        }

        String referrer = context.header("Referer");
        if (referrer != null && referrer.contains("/comunidades/")) {
            String comunidadIdFromReferrer = referrer.substring(referrer.lastIndexOf("/") + 1);
            Long idComunidadSeleccionada = Long.parseLong(comunidadIdFromReferrer);

            Comunidad comunidad = this.repositorioComunidades.obtenerComunidad(idComunidadSeleccionada);

            prestacionesDeServicios = comunidad.getServiciosDeInteres();

            if (comunidad!=null) {
                model.put("comunidadSeleccionada", comunidad);
                prestacionesDeServicios = comunidad.getServiciosDeInteres();
            }

        }

        model.put("comunidades", comunidades);
        model.put("servicios", prestacionesDeServicios);

        context.render("incidentes/aperturaIncidentes.hbs", model);
    }

    @Override
    public void save(Context context) {
        Usuario usuarioLogueado =  super.usuarioLogueado(context);
        PrestacionDeServicio servicioAfectado = null;
        Comunidad comunidad = null;
        String descripcion = null;

        if(usuarioLogueado == null || !usuarioLogueado.tenesPermiso("reportar_incidentes")) {
            throw new AccessDeniedException();
        }

        if (context.formParam("servicio") != null) {
            Long servicioAfectadoId = (Long) this.repositorioPrestacionesDeServicio.obtenerIdDelServicioPorNombre(context.formParam("servicio"));
            servicioAfectado = (PrestacionDeServicio) this.repositorioPrestacionesDeServicio.buscar(servicioAfectadoId);
        }

        if (context.formParam("comunidad") != null) {
            Long comunidadId = (Long) this.repositorioComunidades.obtenerIdDeComunidadPorNombre(context.formParam("comunidad"));
            comunidad = this.repositorioComunidades.obtenerComunidad(comunidadId);
        }

        if (context.formParam("observaciones") != null) { // creo que esto por ser na string lo vamos a tener que cambiar, eze lo hizo con un object equals
            descripcion = context.formParam("observaciones");
        }

        Incidente incidente = new Incidente(servicioAfectado, usuarioLogueado, comunidad, descripcion);
        //this.asignarParametros(incidente, context); lo vole porque el contructor de incidente tenia mas complejidad kjj
        this.repositorioIncidentes.guardar(incidente);
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
        Usuario usuarioLogueado =  super.usuarioLogueado(context);

        if(usuarioLogueado == null || !usuarioLogueado.tenesPermiso("cerrar_incidentes")) {
            throw new AccessDeniedException();
        }

        EstadoIncidente nuevoEstado = new EstadoIncidente(usuarioLogueado, LocalDateTime.now(), incidente);
        nuevoEstado.setEstado(Estado.RESUELTO);

        repositorioIncidentes.actualizar(incidente);

    }

    @Override
    public void delete(Context context) {

    }


}
