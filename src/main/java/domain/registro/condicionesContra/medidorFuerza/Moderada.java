package domain.registro.condicionesContra.medidorFuerza;

import domain.registro.Contrasenia;

public class Moderada extends MedidorDeFuerza{

    // Para que sea moderada tiene > 10 caracteres, incluye números y letras May y minus

    @Override
    public void fuerza(Contrasenia contrasenia) {

        if (this.cumpleCondicionesParaFuerte(contrasenia)) {
            contrasenia.setFuerza(new Fuerte());
        }
        super.fuerza(contrasenia);
   }

    private boolean cumpleCondicionesParaFuerte(Contrasenia contrasenia) {
        return (contrasenia.getContrasenia().length() > 10 &&
                contrasenia.tieneNum() &&
                contrasenia.tieneMayusyMinus() &&
                contrasenia.tieneCaracterEspecial());
    }
}
