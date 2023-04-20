package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

public class Longitud implements Condicion {

    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {
        try {
            return this.cumpleConLongitud(contrasenia);
        }catch (ContraseniaNoCumpleConLongitudException e){
            System.out.println("La contrasenia no cumple con la longitud necesaria");
        }
        return false;
    }
    public boolean excedeCaracteres(Contrasenia contrasenia)
    {
        return contrasenia.getContrasenia().length() >=64;
    }
    public boolean noAlcanzaCaracteres(Contrasenia contrasenia) {
        return contrasenia.getContrasenia().length() < 8;
    }
    public boolean cumpleConLongitud(Contrasenia contrasenia){

        if((! excedeCaracteres(contrasenia) && ! noAlcanzaCaracteres(contrasenia)))
        {
            return true;
        }
        else{

            throw new ContraseniaNoCumpleConLongitudException("La contraseña etc etc");

        }
    }
}
