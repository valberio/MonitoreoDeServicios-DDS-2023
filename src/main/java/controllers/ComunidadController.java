package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entities.domain.comunidad.Comunidad;
import models.entities.domain.registro.Usuario;
import models.entities.domain.roles.Rol;
import models.entities.domain.servicios.PrestacionDeServicio;
import models.repositories.datos.RepositorioComunidades;
import models.repositories.datos.RepositorioDeRoles;
import models.repositories.datos.RepositorioPrestacionesDeServicio;
import models.repositories.datos.RepositorioUsuarios;
import server.exceptions.AccessDeniedException;
import server.utils.ICrudViewsHandler;

import javax.swing.*;
import java.util.*;

public class ComunidadController extends Controller implements ICrudViewsHandler {
    private RepositorioComunidades repositorioComunidades;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio;

    public ComunidadController(RepositorioComunidades repositorioComunidades, RepositorioUsuarios repositorioUsuarios, RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio) {
        this.repositorioComunidades = repositorioComunidades;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioPrestacionesDeServicio = repositorioPrestacionesDeServicio;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        String id = context.sessionAttribute("id").toString();
        List<Long> idComunidades = this.repositorioComunidades.buscarComunidadesDe(Long.parseLong(id));

        List<Comunidad> comunidades = new ArrayList<>();

        for(Long idComunidad: idComunidades) {

            comunidades.add(this.repositorioComunidades.obtenerComunidad(idComunidad));
        }

        model.put("comunidades", comunidades);
        context.render("comunidades/comunidades.hbs", model);
    }

    @Override
    public void show(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Comunidad> comunidades = this.repositorioComunidades.buscarTodos();
        model.put("comunidades", comunidades);
        context.render("comunidades/unirseAComunidad.hbs", model);

    }

    @Override
    public void create(Context context) {
        //Valido unicamente para SuperAdmin de la plataforma
        Map<String, Object> model = new HashMap<>();
        String id = context.sessionAttribute("id").toString();

            context.render("comunidades/crearComunidades.hbs");

    }

    @Override
    public void save(Context context) {

        Comunidad comunidad = new Comunidad();
        this.asignarParametros(comunidad, context);
        this.repositorioComunidades.guardar(comunidad);
        context.status(HttpStatus.CREATED);
        context.redirect("home");
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

    public void asignarParametros(Comunidad comunidad, Context context){
        if(!Objects.equals(context.formParam("nombre"), "")) {
            comunidad.setNombre(context.formParam("nombre"));
        }
        if(!Objects.equals(context.formParam("descripcion"), "")) {
            comunidad.setDescripcion(context.formParam("descripcion"));
        }

        String[] serviciosInteres = context.formParams("serviciosInteres").toArray(new String[0]);
        List<PrestacionDeServicio> serviciosDeInteres = new ArrayList<>();

        if (serviciosInteres != null) {
            for (String servicioInteresNombre : serviciosInteres) {
                Long servicioInteresId = this.repositorioPrestacionesDeServicio.obtenerIdDelServicioPorNombre(servicioInteresNombre);
                PrestacionDeServicio servicioInteres = (PrestacionDeServicio) this.repositorioPrestacionesDeServicio.buscar(servicioInteresId);
                serviciosDeInteres.add(servicioInteres);
            }
        }
        comunidad.setServiciosDeInteres(serviciosDeInteres);

    }
}
