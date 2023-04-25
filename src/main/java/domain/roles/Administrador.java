package domain.roles;

import domain.registro.Usuario;
import domain.servicios.Servicio;
import domain.transporte.LineaDeTransporte;
import domain.transporte.ServicioPublico;
import domain.transporte.TipoDeTransporte;

public class Administrador extends Rol{

    public void habilitarServicio(Servicio servicio){
        servicio.habilitar();
    }

    public void deshabilitarServicio(Servicio servicio){
        servicio.deshabilitar();
    }

    public void darDeAlta(Usuario usuario){
        comunidad.aniadirMiembro(usuario);
    }

    public void darDeBaja(Usuario usuario){
        comunidad.eliminarMiembro(usuario);
    }

    public void darDeAlta(LineaDeTransporte linea, TipoDeTransporte tipoDeTransporte) {
        ServicioPublico nuevoServicioPublico = new ServicioPublico(linea, tipoDeTransporte);
    }

    public void habilitarServicioPublico(ServicioPublico servicioPublico) {servicioPublico.setEstaHabilitado(true); }

    public void deshabilitarServicioPublico(ServicioPublico servicioPublico) { servicioPublico.setEstaHabilitado(false); }

}
