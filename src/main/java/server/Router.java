package server;



import controllers.*;
import models.repositories.datos.RepositorioUsuarios;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        // index
        Server.app().routes(() -> {
            get("/", ctx -> ctx.render("index/inicioSesion.hbs"));
            post("/", ctx -> {

                // Comprueba si se ha enviado una solicitud POST de inicio de sesión
                if (ctx.req().getMethod().equalsIgnoreCase("POST")) {
                    // Lógica de autenticación, por ejemplo, verificar el usuario y contraseña
                    String username = ctx.formParam("usuario");
                    String password = ctx.formParam("contrasenia");

                    Long id = retornarIDSiCredencialesSonCorrectas(username, password);
                    // Si las credenciales son válidas, establece una sesión para el usuario
                    if (id != null) {
                        ctx.sessionAttribute("authenticated", true);
                        ctx.sessionAttribute("id", id);
                        ctx.redirect("/home");// Redirige al usuario a la página de inicio
                    } else {
                        String error = "Credenciales incorrectas";
                        Map<String, Object> model = new HashMap<>();
                        model.put("error", error);
                        ctx.render("index/inicioSesion.hbs", model);
                    }
                }
            });
            get("signup", ((UsuarioController) FactoryController.controller("Usuario"))::create);
            post("signup", ((UsuarioController) FactoryController.controller("Usuario"))::save);

            get("/entidades", ((EntidadController) FactoryController.controller("Entidad"))::index);
            post("/entidades", ((UsuarioController) FactoryController.controller("Usuario"))::updateEntities);
        });

        Server.app().routes(() -> {
            get("rankings", ctx -> ctx.render("presentacion/rankings.hbs"));
            get("rankings/:tipoRanking", ctx -> {
                String tipoRanking = ctx.queryParam("tipoRanking");
                Path pathAlArchivo = Paths.get("../models/repositories.datos/" + tipoRanking);
                ctx.result();});
        });

        Server.app().routes(() -> {
            get("/comunidades", ctx -> ctx.render("comunidades/comunidades.hbs"));
            get("/comunidades/unirse", ctx -> ctx.render("comunidades/unirseAComunidad.hbs"));
            get("/comunidades/crear", ctx -> ctx.render("comunidades/crearComunidades.hbs"));
        });

        Server.app().routes(() -> {
            get("home", ((UsuarioController) FactoryController.controller("Usuario"))::index);
            get("editar", ((UsuarioController) FactoryController.controller("Usuario"))::edit);
            post("editar", ((UsuarioController) FactoryController.controller("Usuario"))::update);
        });

        Server.app().routes(() -> {
            get("incidentes/crear", ((IncidenteController) FactoryController.controller("Incidente"))::create);
            post("incidentes/crear", ((IncidenteController) FactoryController.controller("Incidente"))::save);
            get("incidentes/{id}/editar", ((IncidenteController) FactoryController.controller("Incidente"))::edit);
            post("incidentes/{id}/editar", ((IncidenteController) FactoryController.controller("Incidente"))::update);
        });

        Server.app().routes(() -> {
            get("cargadatos/", ((CargaOrganismosYEntidadesController) FactoryController.controller("CargaOrganismosYEntidadesController"))::index);
            post("cargadatos/", ((CargaOrganismosYEntidadesController) FactoryController.controller("CargaOrganismosYEntidadesController"))::save);
        });
        // presentacion



        /*((IncidenteController) FactoryController.controller("Incidentes"))
            ((UsuarioController) FactoryController.controller("Usuario"))
            ((EntidadController) FactoryController.controller("Entidad"))
            ((OrganismoDeController) FactoryController.controller("OrganismoDeControl"))
         */

    }

    private static Long retornarIDSiCredencialesSonCorrectas(String username, String password) {

        UsuarioController controller = (UsuarioController) FactoryController.controller("Usuario");

        Long id = controller.retornarIdSiExiste(username);

        if(id==null) {
            //todo handle excepcion de flaco vos no existis
            return null;
        }
        
        else if(controller.esCorrecta(username, password)) {
            return id; 
        }
       else  {
            return null;
            //todo handle exception de flaco tirame bien la contra
        }

    }

    private static Long retornarIDPostRegistro(String username) {

        UsuarioController controller = (UsuarioController) FactoryController.controller("Usuario");

        return controller.retornarIdSiExiste(username);

    }
}