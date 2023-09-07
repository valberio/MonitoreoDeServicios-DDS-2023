package tests.domain.api;

import domain.comunidad.Comunidad;
import domain.entidades.Establecimiento;
import domain.incidentes.Incidente;
import domain.registro.Usuario;
import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.*;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class APITest {
    public APITest() throws IOException {
    }
    @Test
    public void estasCercaDeDevuelveVerdadero() throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();

        Ubicacion ubicacion1 = servicioGeoref.obtenerDetallesUbicacion(-34.75, -58.4);
        Ubicacion ubicacion2 = servicioGeoref.obtenerDetallesUbicacion(-34.756, -58.5);

        Assertions.assertTrue(ubicacion1.estasCercaDe(ubicacion2));
    }

    @Test
    public void noEstasCercaDeDevuelveFalso() throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();

        Ubicacion ubicacion1 = servicioGeoref.obtenerDetallesUbicacion(-34.6, -58.4);
        Ubicacion ubicacion2 = servicioGeoref.obtenerDetallesUbicacion(-34.75, -58.4);

        Assertions.assertFalse(ubicacion1.estasCercaDe(ubicacion2));
    }
}








