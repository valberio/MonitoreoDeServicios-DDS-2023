package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

import java.util.Objects;

public class RepeticionCaracteres implements Condicion{
    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {
        try{
            this.noRepiteCaracteres(contrasenia);
        }catch (ContraseniaRepiteCaracteresException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*public boolean noRepiteCaracteres(Contrasenia contrasenia) {
        for (int i = 0; i < contrasenia.getContrasenia().length()-2; i++){
            if (Objects.equals(contrasenia.getContrasenia().charAt(i), contrasenia.getContrasenia().charAt(i+1)) && Objects.equals(contrasenia.getContrasenia().charAt(i+1), contrasenia.getContrasenia().charAt(i+2))){
                throw new ContraseniaRepiteCaracteresException("La contraseña repite caracteres");
            }
        }

        return true;
    }*/
    public boolean noRepiteCaracteres(Contrasenia contrasenia)
    {
        if(! this.tieneCaracteresRepetidosXVeces(contrasenia.getContrasenia(), 3))
        {
            return true;
        }
        else
        {
            throw new ContraseniaRepiteCaracteresException("La contraseña repite muchos caracteres");
        }
    }

    public boolean tieneCaracteresRepetidosXVeces(String contrasenia, int nroRepes)
    {
           Boolean hayRepetido = false;

           for (int i = 0; i < contrasenia.length(); i++)
           {
               int contadorRepeticiones = 0;
               for (int j = 0; j <= nroRepes; j++)
               {
                   if (contrasenia.charAt(i) == contrasenia.charAt(j))
                   {
                       contadorRepeticiones++;
                   }
               }
               if (contadorRepeticiones >= nroRepes)
               {
                   hayRepetido = true;
                   break;
               }
           }
           return hayRepetido;
    }
}


