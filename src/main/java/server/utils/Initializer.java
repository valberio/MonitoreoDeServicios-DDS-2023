package server.utils;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.With;
import models.entities.domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import models.entities.domain.roles.Permiso;
import models.entities.domain.roles.Rol;
import models.entities.domain.roles.TipoRol;
import models.repositories.datos.RepositorioDePermisos;

import java.util.ArrayList;
import java.util.List;

public class Initializer implements WithSimplePersistenceUnit {

    public static void init() {
        new Initializer()
                .iniciarTransaccion()
                .permisos()
                .roles()
                .commitearTransaccion();
    }

    private Initializer iniciarTransaccion() {
        entityManager().getTransaction().begin();
        return this;
    }

    private Initializer commitearTransaccion() {
        entityManager().getTransaction().commit();
        return this;
    }

    private Initializer permisos() {
        String[][] permisos = {
                //Miembros de una comunidad
                { "Reportar incidentes en comunidad", "reportar_incidentes"},
                { "Cerrar incidentes", "cerrar_incidentes" },
                {"Ver incidentes en comunidad", "ver_incidentes"},
                //Admin de una comunidad
                { "Editar comunidad", "editar_comunidad" },
                {"Ver miembros de una comunidad", "ver_participantes"},
                {"Validar publicaciones de los miembros de la comunidad", "controlar_miembros"},
                {"Designar servicios de interes para la comunidad", "registrar_servicios"},

                //SuperAdmin
                { "Cargar archivo CSV", "cargar_csv" },
                {"Crear comunidades", "crear_comunidades"},

                //Ver rankings (?
                {"Ver rankings", "ver_rankings"}
        };

        for(String[] unPermiso: permisos) {
            Permiso permiso = new Permiso();
            permiso.setNombre(unPermiso[0]);
            permiso.setNombreInterno(unPermiso[1]);
            entityManager().persist(permiso);
        }

        return this;
    }


    private Initializer roles() {
        RepositorioDePermisos buscadorDePermisos = new RepositorioDePermisos();


        Rol superAdmin = new Rol();
        superAdmin.setNombre("Super Administrador de la Plataforma");
        superAdmin.setTipo(TipoRol.SUPERADMINISTRADOR);
        superAdmin.agregarPermisos(buscadorDePermisos.buscarPermisoPorNombre("cargar_csv"),buscadorDePermisos.buscarPermisoPorNombre("crear_comunidades"));
        entityManager().persist(superAdmin);



        return this;
    }

    public static List<String> obtenerMedioDeNotificacionValidos() {

       ArrayList<String> medios = new ArrayList<>();

       medios.add("WhatsApp"); 
       
       medios.add("Mail"); 
        
       return medios; 

    }
    
    public static List<String> obtenerModosDeRecepcionValidos() {

        ArrayList<String> modos = new ArrayList<>();

        modos.add("Sincronica");

        modos.add("Asincronica");

        return modos;

    }
}
