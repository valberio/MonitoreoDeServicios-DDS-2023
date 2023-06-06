package tests.domain.api;

import com.opencsv.exceptions.CsvValidationException;
import domain.Localizacion.Localizacion;
import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

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
}



