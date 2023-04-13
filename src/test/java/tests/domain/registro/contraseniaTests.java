package tests.domain.registro;

import domain.registro.Contrasenia;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class contraseniaTests {

    private Contrasenia contraseniaNOAcortable = new Contrasenia("1234    ");
    private Contrasenia contraseniaAcortable = new Contrasenia("12345678  ");

    @Test
    public void test()
    {
        contraseniaNOAcortable.reducirEspacios();
        Assertions.assertEquals();
    }
}
