package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

public class UsoDeCredenciales implements Condicion {

    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {

        return !contrasenia.utilizaCredencialesPorDefecto();
    }
}
