package domain.notificaciones.creacion;

import domain.incidentes.Incidente;

public class Revision implements ContextoIncidente{
    @Override
    public String obtenerTexto(Incidente incidente) {

        return "¡Hola! Por favor, te solicitamos revisar el funcionamiento del servicio " + incidente.getServicioAfectado() + " en " + incidente.getLocalizacion();
    }
}
