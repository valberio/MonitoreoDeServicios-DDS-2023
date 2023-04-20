package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

public class RepeticionCaracteres implements Condicion{
    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {
        return contrasenia.repiteCaracteres();
    }
}
