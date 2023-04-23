package domain.registro;

import domain.config.Config;
import domain.registro.condicionesContra.*;
import domain.registro.condicionesContra.medidorFuerza.Debil;
import domain.registro.condicionesContra.medidorFuerza.MedidorDeFuerza;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
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
    private ArrayList<Condicion> validador = new ArrayList<>();

    private MedidorDeFuerza fuerza;

    public Contrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
        this.instanciarCondiciones();
        this.fuerza = new Debil();
    }

    private void instanciarCondiciones() {

        this.validador.add(new Longitud());
        this.validador.add(new RepeticionCaracteres());
        this.validador.add(new UsoDeCredenciales());
        this.validador.add(new UsoReiterado());
    }

    //
    public boolean esValida() {

        this.reducirEspacios();
        return validador.stream().allMatch(condicion->condicion.cumpleCondicion(this));
    }


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
        File archivoContrasenias = new File(Config.RUTA_ARCHIVOS + Config.NOMBRE_ARCHIVO);
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

    //Condiciones para medidor de fuerza
    public boolean tieneNum(){
        for(int i = 0; i < this.contrasenia.length(); i++){
            if(java.lang.Character.isDigit(this.contrasenia.charAt(i))){
                return true;
            }
        }
        return false;
    }
    public boolean tieneMayusyMinus() {
        boolean tieneMayus = false;
        boolean tieneMinus = false;

        for (int i = 0; i  < this.contrasenia.length(); i++)
        {
            if(java.lang.Character.isLowerCase(this.contrasenia.charAt(i))) {tieneMinus = true;}
            if(java.lang.Character.isUpperCase(this.contrasenia.charAt(i))) {tieneMayus = true;}
        }

        return tieneMayus && tieneMinus;
    }

    public boolean tieneCaracterEspecial(){

        char[] caracteresEspeciales = {'!', '¡', '?', '¿', '#', '$', '|', '°', '%', '/', '@', '%', '^', '&', '*', '(', ')', '-', '+', '=', '{', '}', '[', ']', ';', ':', '"'};
        for(char c: contrasenia.toCharArray()) {
            for(char i: caracteresEspeciales)
            {
                if (c == i) {return true;}
            }
        }
        return false;
    }

    public void mostrarFuerza(){
        //this.fuerza.getClassName();
    }

}



