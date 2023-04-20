package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

public class UsoReiterado implements Condicion{


    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {
        return contrasenia.esMuyUsada();
    }
}
