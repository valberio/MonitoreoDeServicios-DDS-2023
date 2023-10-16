package server;



import controllers.*;
import models.repositories.datos.RepositorioUsuarios;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import models.entities.domain.roles.TipoRol;
import server.exceptions.AccessDeniedException;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

    public static void init() {
        // index
        Server.app().before("/entidades", ctx -> {

            boolean usuarioRegistrado = ctx.sessionAttribute("id") != null;

            if (!usuarioRegistrado) {
                throw new AccessDeniedException();
            }
        });

        Server.app().routes(() -> {
            get("/", ctx -> ctx.render("index/inicioSesion.hbs"));
            post("/", ctx -> {

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

            });
            get("signup", ((UsuarioController) FactoryController.controller("Usuario"))::create);
            post("signup", ((UsuarioController) FactoryController.controller("Usuario"))::save);

            get("/entidades", ((EntidadController) FactoryController.controller("Entidad"))::index);
            post("/entidades", ((UsuarioController) FactoryController.controller("Usuario"))::updateEntities);
        });

        Server.app().routes(() -> {
            get("rankings", ctx -> ctx.render("presentacion/rankings.hbs"));
            get("rankings/rankingReportes", ctx -> {
                Path pathAlArchivo = Paths.get("src/main/java/models/repositories/datos/rankingReportes.csv");
                if (Files.exists(pathAlArchivo)) {
                    ctx.result(new FileInputStream(pathAlArchivo.toFile()));
                    ctx.header("Content-Disposition", "attachment; filename=rankingReportes.csv");
                    ctx.contentType(Files.probeContentType(pathAlArchivo));
                } else {
                    ctx.status(404).result("File not found");
                }});
            get("rankings/rankingImpactoIncidentes", ctx -> {
                Path pathAlArchivo = Paths.get("src/main/java/models/repositories/datos/rankingImpactoIncidentes.csv");
                if (Files.exists(pathAlArchivo)) {
                    ctx.result(new FileInputStream(pathAlArchivo.toFile()));
                    ctx.header("Content-Disposition", "attachment; filename=rankingImpactoIncidentes.csv");
                    ctx.contentType(Files.probeContentType(pathAlArchivo));
                } else {
                    ctx.status(404).result("File not found");
                }});
            get("rankings/rankingPromedioCierre", ctx -> {
                Path pathAlArchivo = Paths.get("src/main/java/models/repositories/datos/rankingPromedioCierre.csv");
                if (Files.exists(pathAlArchivo)) {
                    ctx.result(new FileInputStream(pathAlArchivo.toFile()));
                    ctx.header("Content-Disposition", "attachment; filename=rankingPromedioCierre.csv");
                    ctx.contentType(Files.probeContentType(pathAlArchivo));
                } else {
                    ctx.status(404).result("File not found");
                }});
        });

        Server.app().routes(() -> {
            get("/comunidades", ((ComunidadController) FactoryController.controller("Comunidad"))::index);
            get("/comunidades/unirse", ((ComunidadController) FactoryController.controller("Comunidad"))::show);
            get("/comunidades/{id}", ((ComunidadController) FactoryController.controller("Comunidad"))::showById);
            post("/comunidades/unirse", ((UsuarioController) FactoryController.controller("Usuario"))::joinCommunity);
            get("/comunidades/crear", ((ComunidadController) FactoryController.controller("Comunidad"))::create, TipoRol.SUPERADMINISTRADOR);
            post("/comunidades/crear", ((ComunidadController) FactoryController.controller("Comunidad"))::save, TipoRol.SUPERADMINISTRADOR);
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
            get("cargadatos", ((CargaOrganismosYEntidadesController) FactoryController.controller("CargaOrganismosYEntidadesController"))::index);
            post("cargadatos", ((CargaOrganismosYEntidadesController) FactoryController.controller("CargaOrganismosYEntidadesController"))::save);
        });
    }

    private static Long retornarIDSiCredencialesSonCorrectas(String username, String password) {

        UsuarioController controller = (UsuarioController) FactoryController.controller("Usuario");

        Long id = controller.retornarIdSiExiste(username);

        if(controller.esCorrecta(username, password)) {
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