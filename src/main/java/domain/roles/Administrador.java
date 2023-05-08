package domain.roles;

import domain.comunidad.Comunidad;
import domain.registro.Usuario;
import domain.servicios.Servicio;
import domain.transporte.Entidad;
import domain.transporte.ServicioPublico;

import java.util.ArrayList;

public class Administrador {

    Usuario usuario;
    ArrayList<Comunidad> comunidades = new ArrayList<>();
    public void habilitarServicio(Servicio servicio){
        servicio.habilitar();
    }

    public void deshabilitarServicio(Servicio servicio){
        servicio.deshabilitar();
    }

    public void darDeAlta(Miembro miembro, Comunidad comunidad){
        int i;

        for (i=0; i<comunidades.size();i++) {

            if(comunidades.get(i).equals(comunidad)) {
                comunidades.get(i).aniadirMiembro(miembro);
            }
        }

    }

    public void darDeBaja(Miembro miembro, Comunidad comunidad){
        int i;

        for (i=0; i<comunidades.size();i++) {

            if(comunidades.get(i).equals(comunidad)) {
                comunidades.get(i).eliminarMiembro(miembro);
            }
        }

    }

    public void darDeAlta(Entidad linea) {
        ServicioPublico nuevoServicioPublico = new ServicioPublico(linea);
    }

    public void habilitarServicioPublico(ServicioPublico servicioPublico) {servicioPublico.setEstaHabilitado(true); }

    public void deshabilitarServicioPublico(ServicioPublico servicioPublico) { servicioPublico.setEstaHabilitado(false); }

}
