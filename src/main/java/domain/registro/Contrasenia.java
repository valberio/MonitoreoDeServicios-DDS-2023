package domain.registro;

import domain.registro.condicionesContra.*;
import domain.registro.condicionesContra.medidorFuerza.Debil;
import domain.registro.condicionesContra.medidorFuerza.MedidorDeFuerza;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.*;
import java.lang.*;


@Getter
@Setter
public class Contrasenia {

    private String contrasenia;

    @Transient
    private Usuario usuario;

    @Transient
    private Validador validador = new Validador();

    @Transient
    private MedidorDeFuerza fuerza;

    public Contrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
        this.fuerza = new Debil();
    }


    //
    public boolean esValida() {

        this.reducirEspacios();
        return validador.esValida(this);

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

    public void mostrarFuerza() {

        this.fuerza.fuerza(this);
    }
}



