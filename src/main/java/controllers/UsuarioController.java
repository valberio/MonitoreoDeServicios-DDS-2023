package controllers;

import io.javalin.http.HttpStatus;
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

     public UsuarioController(RepositorioUsuarios repositorioDeUsuarios) {
          this.repositorioUsuarios = repositorioDeUsuarios;
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
          context.render("index/registro.hbs");

     }

     @Override
     public void save(Context context) {

          Usuario usuario= new Usuario();
          //sacar datos del form del context
          this.asignarParametros(usuario, context);
          // TODO: ELegir que hacer con la localizacion usuario.setLocalizacion(context.formParam("localizacion"));

          repositorioUsuarios.agregarUsuario(usuario);
          context.status(HttpStatus.CREATED);
          context.redirect("/home");
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

     public void addServices(Context context) {

     }

     private void asignarParametros(Usuario usuario, Context context) {
          if(!Objects.equals(context.formParam("nombre"), "")) {
               usuario.setNombreDeUsuario(context.formParam("nombre"));
          }
          if(!Objects.equals(context.formParam("contrasena"), "")) {
               usuario.setContra(context.formParam("contrasena"));
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
