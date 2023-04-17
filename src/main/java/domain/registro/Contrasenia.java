package domain.registro;


import java.util.Objects;
import java.lang.*;
// IMPORTS PARA ARCHIVOS
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;
//
public class Contrasenia {

    protected String contrasenia;

/* COSAS A CUMPLIR
* Más de 8 caracteres hasta 64
* verificar que no esté en las 10.000 contras mas usadas
* aceptar carateres del ascii y espacio
* CUESTION DE ESPACIOS CONSECUTIVOS ()
* No itilizar credenciales x defecto de sw
* Medidor de fuerza de la contraseña (fuerte, normal, débil)
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
    
    //VERIFICACION DE QUE UNA CONTRASEÑA NO ESTE ENTRE LAS 10000 MAS USADAS
    public boolean laContraseniaEsMuyUsada () {
        File archivoContrasenias = new File("RUTA_DEL_ARCHIVO_DE_CONTRASEÑAS_MAS_USADAS/Archivo.txt");
        try {
            // SI EXISTE EL ARCHIVO
            if(archivoContrasenias.exists()) {
                // ABRE LECTURA DEL ARCHIVO
                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivoContrasenias));
                // LINEA LEIDA
                String lineaLeida;
                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                while((lineaLeida = leerArchivo.readLine()) != null) {
                    lineasTotales = lineasTotales + 1;
                    String[] contraseniasUsadas = lineaLeida.split(" ");
                    for(int i = 0 ; i < contraseniasUsadas.length ; i++) {
                        if(palabras[i].equals(contrasenia)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

// COSAS QUE HAY QUE VERIFICAR
/*  Contraseñas obtenidas de corpus de violaciones anteriores.
    Palabras de diccionario.
    Caracteres repetitivos o secuenciales (por ejemplo, "aaaaaa", "1234abcd").
    Palabras específicas del contexto, como el nombre del servicio, el nombre de usuario y sus derivados.
 */



