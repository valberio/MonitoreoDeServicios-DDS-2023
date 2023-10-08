package controllers;

import models.repositories.datos.RepositorioUsuarios;
import server.utils.ICrudViewsHandler;
import models.entities.domain.registro.Usuario;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class UsuarioController extends Controller implements ICrudViewsHandler {
     private RepositorioUsuarios repositorioUsuarios;

     public UsuarioController(RepositorioUsuarios repositorioDeServicios) {
          this.repositorioUsuarios = repositorioDeServicios;
     }

     @Override
     public void index(Context context) {

         IncidenteController incidenteController = (IncidenteController) FactoryController.controller("Incidente");
         incidenteController.index(context);
         context.render("presentacion/menuPrincipal.hbs");
     }

     @Override
     public void show(Context context) {

     }

     @Override
     public void create(Context context) {

     }

     @Override
     public void save(Context context) {

     }

     @Override
     public void edit(Context context) {
          Usuario usuario = (Usuario) this.repositorioUsuarios.buscar(Long.parseLong(context.pathParam("id")));
          Map<String, Object> model = new HashMap<>();
          model.put("usuario", usuario);
          context.render("presentacion/editarPerfil.hbs", model);

     }

     @Override
     public void update(Context context) {
          Usuario usuario = (Usuario) this.repositorioUsuarios.buscar(Long.parseLong(context.pathParam("id")));
          this.asignarParametros(usuario, context);
          this.repositorioUsuarios.actualizar(usuario);
          context.redirect("/index");
     }

     @Override
     public void delete(Context context) {
         Usuario usuario = (Usuario) this.repositorioUsuarios.buscar(Long.parseLong(context.pathParam("id")));
          usuario.setEstaActivo(false);
          this.repositorioUsuarios.actualizar(usuario);
          context.redirect("/");
     }

     private void asignarParametros(Usuario usuario, Context context) {
          if(!Objects.equals(context.formParam("nombre"), "")) {
               usuario.setNombreDeUsuario(context.formParam("nombre"));
          }
          if(!Objects.equals(context.formParam("contraseña"), "")) {
               usuario.setContra(context.formParam("contraseña"));
          }
          if(!Objects.equals(context.formParam("email"), "")) {
               usuario.setNombreDeUsuario(context.formParam("email"));
          }
          if(!Objects.equals(context.formParam("telefono"), "")) {
               usuario.setNombreDeUsuario(context.formParam("telefono"));
          }
          if(!Objects.equals(context.formParam("medio"), "")) {
               usuario.setMedioDeNotificacion(context.formParam("medio"));
          }
          if(!Objects.equals(context.formParam("modo"), "")) {
               usuario.setModo(context.formParam("modo"));
          }
     }


}
