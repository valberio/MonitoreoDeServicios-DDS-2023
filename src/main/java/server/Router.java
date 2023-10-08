package server;


import controllers.FactoryController;
import controllers.UsuarioController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {

        // index
        Server.app().routes(()-> {
            get("/", ctx -> ctx.render("index/inicioSesion.hbs"));
            get("/signup", ctx->ctx.render("index/registro.hbs"));
            get("/servicios", ctx->ctx.render("index/registroSvDeInteres.hbs"));
        });

        Server.app().routes(()-> {
            get("/rankings", ctx->ctx.render("presentacion/rankings.hbs"));
        });

        Server.app().routes(()-> {
            get("/comunidades", ctx->ctx.render("comunidades/comunidades.hbs"));
        });

        Server.app().routes(() -> {
            get("usuarios/{id}", ((UsuarioController) FactoryController.controller("Usuario"))::index);
            get("usuarios/{id}/editar", ((UsuarioController) FactoryController.controller("Usuario"))::edit);
            post("usuarios/{id}/editar", ((UsuarioController) FactoryController.controller("Usuario"))::update);

        });

        // presentacion

        Server.app().get("/saludo-para/{nombre}", ctx -> ctx.result("Hola "
                + ctx.pathParam("nombre")
        ));

        /*((IncidenteController) FactoryController.controller("Incidentes"))
            ((UsuarioController) FactoryController.controller("Usuario"))
            ((EntidadController) FactoryController.controller("Entidad"))
            ((OrganismoDeController) FactoryController.controller("OrganismoDeControl"))
         */

    }
}