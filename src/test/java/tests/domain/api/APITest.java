package tests.domain.api;

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
    public void probarLlamadasMunicipios() throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();
        servicioGeoref.listadoDeMunicipios(10);

        Municipio municipio = new Municipio();

        System.out.println(municipio.obtenerNombre(10));

        Assertions.assertEquals(municipio.obtenerNombre(10), "Belén");
    }
    @Test
    public void testearLlamadasProvincias() throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();
        servicioGeoref.listadoDeProvincias();
        Provincia provincia = new Provincia();
        System.out.println(provincia.obtenerNombre(22));
        Assertions.assertEquals(provincia.obtenerNombre(22), "Buenos Aires");
    }

    @Test
    public void testearLocalizacion() throws IOException {
        Provincia provincia = new Provincia();
        System.out.println(provincia.obtenerNombre(1));
    }

    @Test
    public void testearEstasCerca(){
        Provincia provincia = new Provincia();
        provincia.id = 1;
        Provincia provincia1 = new Provincia();
        provincia1.id = 1;

        Assertions.assertTrue(provincia.estasCercaDe(provincia1));
    }

    @Test
    public void testearNotificaciones(){

        Provincia provincia = new Provincia();
        provincia.id = 10;

        Servicio servicio = new Servicio("Baño", "Baño");

        Establecimiento establecimiento = new Establecimiento("Facultad", provincia);

        PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio(servicio, establecimiento);

        Incidente incidente = new Incidente(prestacionDeServicio, new Usuario("pepe", null, null, null), LocalDateTime.now());

        Usuario usuario = new Usuario("Pepita", null, null, null);
        usuario.modificarLocalizacion(provincia);
    }
}



