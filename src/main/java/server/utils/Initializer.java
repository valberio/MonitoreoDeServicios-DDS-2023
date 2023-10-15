package server.utils;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.With;
import models.entities.domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import models.entities.domain.roles.Permiso;
import models.entities.domain.roles.Rol;
import models.entities.domain.roles.TipoRol;

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
                { "Ver entidades", "ver_servicios" },
                { "Crear comunidades", "crear_comunidades" },
                { "Editar servicios", "editar_servicios" },
                { "Eliminar servicios", "eliminar_servicios" },
        };

        for(String[] unPermiso: permisos) {
            Permiso permiso = new Permiso();
            permiso.setNombre(unPermiso[0]);
            permiso.setNombreInterno(unPermiso[1]);
            entityManager().persist(permiso);
        }

        return this;
    }

    private interface BuscadorDePermisos extends WithSimplePersistenceUnit {
        default Permiso buscarPermisoPorNombre(String nombre) {
            return (Permiso) entityManager()
                    .createQuery("from Permiso where nombreInterno = :nombre")
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        }
    }

    private Initializer roles() {
        BuscadorDePermisos buscadorDePermisos = new BuscadorDePermisos() {};

        Rol administrador = new Rol();
        administrador.setNombre("Administrador");
        administrador.setTipo(TipoRol.ADMINISTRADOR);
        //administrador.agregarPermisos(
                //buscadorDePermisos.buscarPermisoPorNombre("crear_servicios")
       // );
        entityManager().persist(administrador);

        Rol usuarioFinal = new Rol();
        usuarioFinal.setNombre("Usuario");
        usuarioFinal.setTipo(TipoRol.NORMAL);
        // usuarioFinal.agregarPermisos(
                //buscadorDePermisos.buscarPermisoPorNombre("ver_servicios")
        //);
        entityManager().persist(usuarioFinal);

       /* Rol prestador = new Rol();
        prestador.setNombre("Prestador");
        prestador.setTipo(TipoRol.NORMAL);
        prestador.agregarPermisos(
                buscadorDePermisos.buscarPermisoPorNombre("ver_servicios")
        );
        entityManager().persist(prestador);*/

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
