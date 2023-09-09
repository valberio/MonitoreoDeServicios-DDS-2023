package domain.registro.condicionesContra;

import domain.registro.Contrasenia;

import java.util.Objects;

public class UsoDeCredenciales implements Condicion {

    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {
        try{
            this.utilizaCredencialesPorDefecto(contrasenia);
        }catch (ContraseniaUtilizaCredencialesPorDefectoException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean utilizaCredencialesPorDefecto(Contrasenia contrasenia) {
        return Objects.equals(contrasenia.getUsuario().getNombreDeUsuario(), contrasenia.getContrasenia());
    }

}
