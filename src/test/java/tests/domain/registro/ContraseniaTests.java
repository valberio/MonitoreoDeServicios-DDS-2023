package tests.domain.registro;

import com.opencsv.exceptions.CsvValidationException;
import domain.registro.Contrasenia;
import domain.registro.Usuario;
import domain.registro.Validador;
import domain.registro.condicionesContra.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import domain.entidades.cargaEntidadesyEntesReguladores;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContraseniaTests {

    private Contrasenia contraseniaNOAcortable = new Contrasenia("1234    ");
    private Contrasenia contraseniaAcortable = new Contrasenia("12345678  ");
    private Contrasenia contraseniaCorta = new Contrasenia("1234");
    private Contrasenia contraseniaLarga = new Contrasenia("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    private Contrasenia contraseniaConRepes = new Contrasenia("1111234678");
    private Contrasenia contraseniaSinRepes = new Contrasenia("12345678");
    private Contrasenia contraseniaEspecial = new Contrasenia("123#");
    private Contrasenia contraseniaValida = new Contrasenia("Abcd12345_");

    private Validador validador = new Validador();
    private Longitud longitud = new Longitud();
    private RepeticionCaracteres repeticiones = new RepeticionCaracteres();
    private UsoDeCredenciales usoDeCredenciales = new UsoDeCredenciales();
    private UsoReiterado usoReiterado = new UsoReiterado();

    private void instanciarCondiciones() {
        this.validador.agregarCondiciones(longitud, repeticiones, usoDeCredenciales, usoReiterado);
    }
    private Usuario usuarioValido = new Usuario("Pepito", contraseniaValida, "pepito@gmail.com");

    @Test
    public void testReducirEspaciosCuandoSeaNecesario()
    {
        contraseniaNOAcortable.reducirEspacios();

        Assertions.assertEquals(contraseniaNOAcortable.getContrasenia(), "1234");
    }

    @Test //Verificando que las contraseñas cortas lancen una expecion
    public void testLanzarExcepcionContraCorta()
    {
        assertThrows(ContraseniaNoCumpleConLongitudException.class, () -> {
            longitud.cumpleConLongitud(contraseniaCorta);
        }, "Se espera que se lance una excepcion");
    }

    public void testClaveMuyLarga()
    {
        Assertions.assertThrows(ContraseniaNoCumpleConLongitudException.class, () ->
        {longitud.cumpleConLongitud(contraseniaLarga);
        }, "Se espera que se lance una excepcion");
    }

    @Test
    public void testContraseniaEsValida(){
        this.instanciarCondiciones();
        Assertions.assertFalse( contraseniaCorta.esValida());
    }
    @Test
    public void testContraseniaNoEsValida(){
        this.instanciarCondiciones();
        Assertions.assertFalse(contraseniaCorta.esValida());
    }
    @Test
    public void testClaveConRepeticiones()
    {
        Assertions.assertTrue(repeticiones.tieneCaracteresRepetidosXVeces(contraseniaConRepes.getContrasenia(), 3));
    }
    @Test
    public void testClaveConRepeticionesException()
    {
        Assertions.assertThrows(ContraseniaRepiteCaracteresException.class, () -> {
            repeticiones.noRepiteCaracteres(contraseniaConRepes);
        }, "Se espera que se lance una excepcion");
    }
    @Test
    public void testClaveSinRepeticionesException()
    {
        Assertions.assertTrue(repeticiones.noRepiteCaracteres(contraseniaSinRepes));
    }
    @Test
    public void testClaveTieneCaracEspecial()
    {
        Assertions.assertTrue(contraseniaEspecial.tieneCaracterEspecial());
    }
    @Test
    public void testNoTieneCaracEspecial()
    {
        Assertions.assertFalse(contraseniaCorta.tieneCaracterEspecial());
    }

    @Test
    public void testCarga() throws CsvValidationException, IOException {
            cargaEntidadesyEntesReguladores carga = new cargaEntidadesyEntesReguladores();
            carga.CargarEntitidadesYEntesReguladores();
        }
}

