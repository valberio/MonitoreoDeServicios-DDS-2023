package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

import java.util.Objects;
/*
public class RepeticionCaracteres implements Condicion{
    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {
        try{
            repiteCaracteres(contrasenia);
        }catch (ContraseniaRepiteCaracteresException e){
            System.out.println("La contrasenia repite caracateres");
        }
        return false;
    }
    // TENGO QUE REPENSAR LA LOGICA DE CUANDO DEBERIA SALIR LA EXCEPCION
    public boolean repiteCaracteres(Contrasenia contrasenia) {
        for (int i = 0; i < contrasenia.getContrasenia().length()-2; i++){
            if (Objects.equals(contrasenia.getContrasenia().charAt(i), contrasenia.getContrasenia().charAt(i+1)) && Objects.equals(contrasenia.getContrasenia().charAt(i+1), contrasenia.getContrasenia().charAt(i+2))){
                throw new ContraseniaRepiteCaracteresException("La contraseña etc etc");
            }
        }

    }
}
*/

