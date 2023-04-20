package domain.registro.condicionesContra.medidorFuerza;

import domain.registro.Contrasenia;

public class Debil implements MedidorDeFuerza {
    //Para que sea debil está entre 8 y 10 caracteres}

    public void fuerza(Contrasenia contrasenia) {
        if (this.cumpleCondicionesParaModerada(contrasenia)) {
            contrasenia.setFuerza(new Moderada());
        }
    }

    private boolean cumpleCondicionesParaModerada(Contrasenia contrasenia) {
        return (contrasenia.getContrasenia().length() > 10 &&
                contrasenia.tieneNum() &&
                contrasenia.tieneMayusyMinus());
    }
}
