package tests.domain.api;

import com.opencsv.exceptions.CsvValidationException;
import domain.Localizacion.Localizacion;
import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.Provincia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import domain.services.georef.entities.ListadoDeDepartamentos;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class APITest {
    private Entidad lineaMitre = new Entidad("Linea Mitre");
    //private Provincia buenosAires = new Provincia(06);


    public APITest() throws IOException {
    }


    @Test
    public void localizacionProvincia() throws IOException{

        ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();
        ListadoDeProvincias listadoDeProvincias = servicioGeoref.listadoDeProvincias(6);
        ListadoDeDepartamentos listadoDeDepartamentos = servicioGeoref.listadoDeDepartamentos(5);
        ListadoDeMunicipios listadoDeMunicipios = servicioGeoref.listadoDeMunicipios(10);


        /*Entidad entidad1 = new Entidad("Linea Mitre", null, tipoEntidad);
        Entidad entidad2 = new Entidad("Linea Otro", null, tipoEntidad);
        Entidad entidad3 = new Entidad("Linea No Se",null, tipoEntidad);*/

        Establecimiento establecimiento1 = new Establecimiento("Estación de Banfield");
        Establecimiento establecimiento2 = new Establecimiento("Estación de Lanús");
        Establecimiento establecimiento3 = new Establecimiento("Estación de Lomas de Zamora");

        Provincia bsas = new Provincia();
        bsas.obtenerse(6);
        System.out.println(bsas.obtenerNombre(6));
        establecimiento1.setUbicacionGeografica(bsas);
        System.out.println(establecimiento1.getUbicacionGeografica().obtenerNombre(6));
        /*establecimiento1.setUbicacionGeografica(listadoDeProvincias.provincias.stream().findFirst().get());
        establecimiento2.setUbicacionGeografica(listadoDeDepartamentos.departamentos.stream().findFirst().get());
        establecimiento3.setUbicacionGeografica(listadoDeMunicipios.municipios.stream().findFirst().get());*/

    }
}



