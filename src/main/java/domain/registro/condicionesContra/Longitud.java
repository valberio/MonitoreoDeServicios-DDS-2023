package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

public class Longitud implements Condicion {

    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {

        return !contrasenia.esDebil() && !contrasenia.excedeCaracteres();

    }

}
