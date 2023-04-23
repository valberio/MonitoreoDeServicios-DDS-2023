package domain.registro.condicionesContra.medidorFuerza;


import domain.registro.Contrasenia;

public abstract class MedidorDeFuerza {

    public void fuerza(Contrasenia contrasenia) {

        String className = this.getClass().getSimpleName();
        System.out.println("Contrasenia:" + className);

    }


}
