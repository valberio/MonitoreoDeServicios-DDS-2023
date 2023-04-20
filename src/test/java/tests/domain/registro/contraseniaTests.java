package tests.domain.registro;

import domain.registro.Contrasenia;
import domain.registro.condicionesContra.Longitud;
import domain.registro.condicionesContra.Condicion;
import domain.registro.condicionesContra.ContraseniaNoCumpleConLongitudException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class contraseniaTests {

    private Contrasenia contraseniaNOAcortable = new Contrasenia("1234    ");
    private Contrasenia contraseniaAcortable = new Contrasenia("12345678  ");
    private Contrasenia contraseniaCorta = new Contrasenia("1234");

    private Longitud longitud = new Longitud();
    @Test
    public void test()
    {
        contraseniaNOAcortable.reducirEspacios();

        //Assertions.assertEquals();
    }

    @Test
    public void test1()
    {

        assertThrows(ContraseniaNoCumpleConLongitudException.class, () -> {
            longitud.cumpleCondicion(contraseniaAcortable);
        }, "Expected MyException to be thrown");
    }
}
