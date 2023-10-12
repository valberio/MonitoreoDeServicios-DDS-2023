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
          //sacar datos del form del contexto

          this.asignarParametros(usuario, context);

          // TODO: ELegir que hacer con la localizacion usuario.setLocalizacion(context.formParam("localizacion"));
          repositorioUsuarios.agregarUsuario(usuario);
          context.status(HttpStatus.CREATED);
          context.redirect("/home");
     }

     @Override
     public void edit(Context context) {
          String id = Objects.requireNonNull(context.sessionAttribute("id")).toString();
          Usuario usuario = (Usuario) this.repositorioUsuarios.buscar(Long.parseLong(id));
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

     public Long retornarIdSiExiste(String username){

          List<Usuario> usuarios = repositorioUsuarios.filtrarPorNombre(username);

          if(!usuarios.isEmpty()) {
               return usuarios.get(0).getId();
          }

          else
               return null;

     }



     private void asignarParametros(Usuario usuario, Context context) {
          if(!Objects.equals(context.formParam("nombre"), "")) {
               usuario.setNombreDeUsuario(context.formParam("nombre"));
          }
          if(!Objects.equals(context.formParam("contrasenia"), "")) {
               usuario.setContra(context.formParam("contrasenia"));
          }
          if(!Objects.equals(context.formParam("email"), "")) {
               usuario.setEmail(context.formParam("email"));
          }
          if(!Objects.equals(context.formParam("telefono"), "")) {
               usuario.setNumeroTelefono(context.formParam("telefono"));
          }
          if(!Objects.equals(context.formParam("localizacion"), "")) {
               //todo;
          }
         // if(!Objects.equals(context.formParam("medio"), "")) {
              // usuario.setMedioDeNotificacion(context.formParam("medio"));
         // }
          //if(!Objects.equals(context.formParam("modo"), "")) {
              // usuario.setModo(context.formParam("modo"));
         // } //TODO
     }


     public boolean esCorrecta(String username, String password) {

          List<Usuario> usuarios = repositorioUsuarios.filtrarPorNombre(username);

          return usuarios.get(0).getContrasenia().getContrasenia().equals(password);
     }
}
