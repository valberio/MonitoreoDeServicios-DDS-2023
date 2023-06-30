package domain.notificaciones.creacion;

import domain.incidentes.Incidente;

public class Creacion implements ContextoIncidente {

    @Override
    public String obtenerTexto(Incidente incidente) {

        return "Un usuario reporto que no funciona el  " + incidente.getServicioAfectado().obtenerTextoRelevante();
    }
}
