package models.entities.domain.registro.condicionesContra.medidorFuerza;

import models.entities.domain.registro.Contrasenia;

public class Debil extends MedidorDeFuerza {

    //Para que sea debil está entre 8 y 10 caracteres

    @Override
    public void fuerza(Contrasenia contrasenia) {
        if (this.cumpleCondicionesParaModerada(contrasenia)) {
            contrasenia.setFuerza(new Moderada());
        }
        super.fuerza(contrasenia);
    }

    private boolean cumpleCondicionesParaModerada(Contrasenia contrasenia) {
        return (contrasenia.getContrasenia().length() > 10 &&
                contrasenia.tieneNum() &&
                contrasenia.tieneMayusyMinus());
    }
}
