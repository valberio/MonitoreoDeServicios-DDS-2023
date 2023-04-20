package domain.registro.condicionesContra.medidorFuerza;

import domain.registro.Contrasenia;

public class Moderada implements MedidorDeFuerza{
    // Para que sea moderada tiene > 10 caracteres, incluye números y letras May y minus

    public void fuerza(Contrasenia contrasenia) {
        if (this.cumpleCondicionesParaFuerte(contrasenia)) {
            contrasenia.setFuerza(new Fuerte());
        }
   }

    private boolean cumpleCondicionesParaFuerte(Contrasenia contrasenia) {
        return (contrasenia.getContrasenia().length() > 10 &&
                contrasenia.tieneNum() &&
                contrasenia.tieneMayusyMinus() &&
                contrasenia.tieneCaracterEspecial());
    }
}
