package domain.registro.condicionesContra.medidorFuerza;

import domain.registro.Contrasenia;

public class Debil implements MedidorDeFuerza {
    //Para que sea debil está entre 8 y 10 caracteres

    public void fuerza(Contrasenia contrasenia) {
        if (this.cumpleCondicionesParaModerada(contrasenia)) {
            contrasenia.setFuerza(new Fuerte());
        }
    }

    private boolean cumpleCondicionesParaModerada(Contrasenia contrasenia) {
        // Verificar condiciones fuerte
        //public boolean esModerada(Contrasenia contrasenia){
        // llega a 10 caracteres, incluye números, letras May y minus, tiene 1 caracter especial
        // esDebil &&
        return (contrasenia.getContrasenia().length() > 10 &&
                contrasenia.tieneNum() &&
                contrasenia.tieneMayusyMinus() &&
                contrasenia.tieneCaracterEspecial());
    }
}
