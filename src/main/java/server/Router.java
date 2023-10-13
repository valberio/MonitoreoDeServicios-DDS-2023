package server;



import controllers.EntidadController;
import controllers.FactoryController;
import controllers.IncidenteController;
import controllers.UsuarioController;
import models.repositories.datos.RepositorioUsuarios;
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

            get("/entidades", ((EntidadController) FactoryController.controller("Entidad"))::show);
            post("/entidades", ((UsuarioController) FactoryController.controller("Usuario"))::update);
            //ctx->ctx.render("index/registroEntidadesDeInteres.hbs"));
        });

        Server.app().routes(() -> {
            get("rankings", ctx -> ctx.render("presentacion/rankings.hbs"));
        });

        Server.app().routes(() -> {
            get("comunidades", ctx -> ctx.render("comunidades/comunidades.hbs"));
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
}