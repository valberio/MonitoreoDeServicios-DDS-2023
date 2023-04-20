package domain.registro;

import domain.config.Config;
import domain.registro.condicionesContra.Condicion;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.lang.*;
// IMPORTS PARA ARCHIVOS
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Getter
@Setter
public class Contrasenia {

    private String contrasenia;
    private Usuario usuario;
    private ArrayList<Condicion> validador;

    private String medidorDeFuerza;

    public Contrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public boolean esValida() {

        this.reducirEspacios();
        return validador.stream().allMatch(condicion->condicion.cumpleCondicion(this));
    }

    //NO ME ANIMO A BORRARLO, PERO YA FUE DELAGADO EN LA CONDICION LONGITUD
    /*public boolean excedeCaracteres() {
        return this.contrasenia.length() >=64;
    }
    */
    public boolean repiteCaracteres() {

        for (int i = 0; i < this.contrasenia.length()-2; i++){
            if (Objects.equals(this.contrasenia.charAt(i), this.contrasenia.charAt(i+1)) && Objects.equals(this.contrasenia.charAt(i+1),this.contrasenia.charAt(i+2)));
            {
               return true;
            }
        }

        return false;
    }

    public void reducirEspacios() {
        int largoClaveConEspacios = contrasenia.length();
        int cantEspacios = 0;
        for (int i = 0; i < contrasenia.length(); i++){
            if (java.lang.Character.isWhitespace(contrasenia.charAt(i))){
                cantEspacios++;
            }
        }
        if (largoClaveConEspacios - cantEspacios < 8)
        {
            contrasenia = contrasenia.replace(" ", "");
        }
    }

    public boolean utilizaCredencialesPorDefecto() {
        return Objects.equals(this.usuario.getUsuario(), this.contrasenia);
    }


    
    //VERIFICACION DE QUE UNA CONTRASEÑA NO ESTE ENTRE LAS 10000 MAS USADAS
    public boolean esMuyUsada () {
        File archivoContrasenias = new File(Config.RUTA_ARCHIVOS + "peoresContraseñas.txt");
        try {
            if(archivoContrasenias.exists()) {

                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivoContrasenias));
                // LINEA LEIDA
                String lineaLeida;
                int lineasTotales=0;

                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                while((lineaLeida = leerArchivo.readLine()) != null) {
                    lineasTotales++;
                    String[] contraseniasUsadas = lineaLeida.split(" ");
                    for(int i = 0 ; i < contraseniasUsadas.length ; i++) {
                        if(contraseniasUsadas[i].equals(contrasenia)) {
                            return true;
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }


}

// COSAS QUE HAY QUE VERIFICAR
/*  Contraseñas obtenidas de corpus de violaciones anteriores.
    Palabras de diccionario.
    Caracteres repetitivos o secuenciales (por ejemplo, "aaaaaa", "1234abcd").
    Palabras específicas del contexto, como el nombre del servicio, el nombre de usuario y sus derivados.
 */



