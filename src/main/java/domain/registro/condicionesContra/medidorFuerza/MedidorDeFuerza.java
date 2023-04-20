package domain.registro.condicionesContra.medidorFuerza;


import domain.registro.Contrasenia;

public interface MedidorDeFuerza {

    public void fuerza(Contrasenia contrasenia);




    /*
    public boolean esDebil(Contrasenia contrasenia){
        // es contra valida y (está entre 8 y 10 caracteres)
        //ESTO NO SERVIRÍA PQ VALIDARÍA TODAS
        if (contrasenia.getContrasenia().length()> 8 &&
                contrasenia.getContrasenia().length() <10){
            System.out.println("La contraseña es Debil");
        }
    }*/

    /*
    public boolean esModerada(Contrasenia contrasenia){
        // llega a 10 caracteres, incluye números, letras May y minus, tiene 1 caracter especial
        // esDebil &&
    }

    public boolean esFuerte(Contrasenia contrasenia){
        // llega a 10 caracteres,incluye numeros, letras May y minus, tiene 1 caracter especial,
        // la contra vence a los 90 dias y se crea una nueva que es <> a las ult 5
    }*/
}
