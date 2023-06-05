package tests.domain.api;

import com.opencsv.exceptions.CsvValidationException;
import domain.Localizacion.Localizacion;
import domain.entidades.Entidad;
import domain.services.georef.entities.Provincia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*

public class APITest {
    private Entidad lineaMitre = new Entidad("Linea Mitre");
    private Provincia buenosAires = new Provincia(06);


    public APITest() throws IOException {
    }


    @Test
    public void obtenerLocalizacion() throws IOException {
        Assertions.assertEquals(lineaMitre.getLocalizacion(), buenosAires);
    }
}


 */