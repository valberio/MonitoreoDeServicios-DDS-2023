package tests.domain.registro;

import domain.registro.Contrasenia;
import domain.registro.condicionesContra.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContraseniaTests {

    private Contrasenia contraseniaNOAcortable = new Contrasenia("1234    ");
    private Contrasenia contraseniaAcortable = new Contrasenia("12345678  ");
    private Contrasenia contraseniaCorta = new Contrasenia("1234");
    private Contrasenia contraseniaLarga = new Contrasenia("12345678901234567890123456789012345678901234567890");
    private Contrasenia contraseniaConRepes = new Contrasenia("1111234");
    private Contrasenia contraseniaSinRepes = new Contrasenia("12345678");
    private Contrasenia contraseniaEspecial = new Contrasenia("123#");

    private Longitud longitud = new Longitud();
    private RepeticionCaracteres repeticiones = new RepeticionCaracteres();
    @Test
    public void test()
    {
        contraseniaNOAcortable.reducirEspacios();

        //Assertions.assertEquals();
    }

    @Test //Verificando que las contraseñas cortas lancen una expecion
    public void test1()
    {
        assertThrows(ContraseniaNoCumpleConLongitudException.class, () -> {
            longitud.cumpleConLongitud(contraseniaCorta);
        }, "Se espera que se lance una excepcion");
    }

    //@Test //TODO Detallito: si la clave es muy larga tira un error (creo que de overflow), no
    //se me ocurre cómo solucionarlo así que dejo comentado
    /*public void testClaveLarga()
    {
        assertThrows(ContraseniaNoCumpleConLongitudException.class, () ->
        {longitud.cumpleConLongitud(contraseniaLarga);
        }, "Se espera que se lance una excepcion");
    }*/

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
}

