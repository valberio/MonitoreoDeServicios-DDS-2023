package domain.notificaciones.creacion;


import domain.incidentes.Incidente;

public class Cierre implements ContextoIncidente {
    @Override
    public String obtenerTexto(Incidente incidente) {
        return "¡Funciona nuevamente el incidente en " + incidente.getServicioAfectado().obtenerTextoRelevante() + "!";
    }
}
