package domain.registro;


import java.util.Objects;
import java.lang.*;

public class Contrasenia {

    protected String contrasenia;

/* COSAS A CUMPLIR
* Más de 8 caracteres hasta 64
* verificar que no esté en las 10.000 contras mas usadas
* aceptar carateres del ascii y espacio
* CUESTION DE ESPACIOS CONSECUTIVOS ()
* No itilizar credenciales x defecto de sw
*
* */
    public Contrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public boolean esValida(Usuario usuario) {
        //TODO
        return this.esDebil() && !this.utilizaCredencialesPorDefecto(usuario);
    }

    public boolean repiteCaracteres() {

        for (int i = 0; i < contrasenia.length()-1; i++){
            if (Objects.equals(contrasenia.charAt(i), contrasenia.charAt(i+1)))
            {
               return true;
            }
        }

        return false;

    }
    public boolean esDebil() {
        return this.contrasenia.length() <= 8;
    }

    public boolean utilizaCredencialesPorDefecto(Usuario usuario) {
        return Objects.equals(usuario.getUsuario(), this.contrasenia);
    }

    public void reducirEspacios() {

        int largoClaveConEspacios = contrasenia.length();
        int cantEspacios = 0;
        for (int i = 0; i < contrasenia.length(); i++){
            if (java.lang.Character.isWhitespace(contrasenia.charAt(i)))
            {
                cantEspacios++;
            }
        }
        if (largoClaveConEspacios - cantEspacios < 8)
        {
            contrasenia = contrasenia.replace(" ", "");
        }
    }

    /*
    public boolean aceptaAsciiYEspacios(){
        boolean isAscii = CharMatcher.ascii().matchesAllOf(someString);

        //TODO
        return true;
    }*/
}

// COSAS QUE HAY QUE VERIFICAR
/*  Contraseñas obtenidas de corpus de violaciones anteriores.
    Palabras de diccionario.
    Caracteres repetitivos o secuenciales (por ejemplo, "aaaaaa", "1234abcd").
    Palabras específicas del contexto, como el nombre del servicio, el nombre de usuario y sus derivados.
 */



