package controllers;

import io.javalin.http.Context;
import models.entities.domain.registro.Usuario;
import models.repositories.datos.RepositorioEntidades;
import server.exceptions.AccessDeniedException;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingController extends Controller implements ICrudViewsHandler {


    @Override
    public void index(Context context) {
    }

    @Override
    public void show(Context context) {

        Usuario usuarioLogueado = super.usuarioLogueado(context);

        if(usuarioLogueado == null || !usuarioLogueado.tenesPermiso("ver_rankings_entidades")) {
            throw new AccessDeniedException();
        }

        context.render("presentacion/rankings.hbs");

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
