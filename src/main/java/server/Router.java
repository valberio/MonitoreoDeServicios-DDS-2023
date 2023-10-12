package server;


import com.twilio.rest.verify.v2.service.entity.Factor;
import controllers.FactoryController;
import controllers.IncidenteController;
import controllers.UsuarioController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        // index
        Server.app().routes(()-> {
            get("/", ctx -> ctx.render("index/inicioSesion.hbs"));
            post("/", ctx -> {
                        // Lógica de autenticación, por ejemplo, verificar el usuario y contraseña
                        String username = ctx.formParam("username");
                        String password = ctx.formParam("password");

                        // Si las credenciales son válidas, establece una sesión para el usuario
                        //if (validarCredenciales(username, password)) {
                        //ctx.sessionAttribute("authenticated", true);
                        ctx.redirect("/home"); // Redirige al usuario a la página de inicio
                        //} else {
                        ctx.result("Credenciales incorrectas"); // Manejar credenciales incorrectas
                        //}
            });
            get("signup", ((UsuarioController) FactoryController.controller("Usuario"))::create);
            post("signup", ((UsuarioController) FactoryController.controller("Usuario"))::save);

            get("signup/servicios", ((UsuarioController) FactoryController.controller("Usuario"))::addServices);
                    //ctx->ctx.render("index/registroSvDeInteres.hbs"));
        });

        Server.app().routes(()-> {
            get("rankings", ctx->ctx.render("presentacion/rankings.hbs"));
        });

        Server.app().routes(()-> {
            get("comunidades", ctx->ctx.render("comunidades/comunidades.hbs"));
        });

        Server.app().routes(() -> {
            get("home", ctx -> ctx.render("presentacion/menuPrincipal.hbs"));
            get("home/{id}", ((UsuarioController) FactoryController.controller("Usuario"))::index);
            get("editar", ((UsuarioController) FactoryController.controller("Usuario"))::edit);
            post("editar", ((UsuarioController) FactoryController.controller("Usuario"))::update);

        });

        Server.app().routes(()-> {
            get("incidentes/crear",((IncidenteController) FactoryController.controller("Incidentes"))::create);
            post("incidentes", ((IncidenteController) FactoryController.controller("Incidentes"))::save);
            get("incidentes/{id}/editar", ((IncidenteController) FactoryController.controller("Incidentes"))::edit);
            post("incidentes/{id}/editar", ((IncidenteController) FactoryController.controller("Incidentes"))::update);
        });
        // presentacion



        /*((IncidenteController) FactoryController.controller("Incidentes"))
            ((UsuarioController) FactoryController.controller("Usuario"))
            ((EntidadController) FactoryController.controller("Entidad"))
            ((OrganismoDeController) FactoryController.controller("OrganismoDeControl"))
         */

    }
}