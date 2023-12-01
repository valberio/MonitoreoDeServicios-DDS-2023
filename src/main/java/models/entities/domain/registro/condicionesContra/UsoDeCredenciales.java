package models.entities.domain.registro.condicionesContra;

import models.entities.domain.registro.Contrasenia;

import java.util.Objects;

public class UsoDeCredenciales implements Condicion {

    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia, String nombreUsuario) {
        try {
            return !this.utilizaCredencialesPorDefecto(contrasenia, nombreUsuario);
        } catch (ContraseniaUtilizaCredencialesPorDefectoException e) {
            throw new ContraseniaUtilizaCredencialesPorDefectoException("La contraseña no puede ser igual al nombre de usuario.");
        }
    }

    public boolean utilizaCredencialesPorDefecto(Contrasenia contrasenia, String nombreUsuario) {
        return nombreUsuario.equals(contrasenia.getContrasenia());
    }

}
