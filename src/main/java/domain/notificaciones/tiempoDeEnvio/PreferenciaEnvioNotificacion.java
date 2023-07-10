package domain.notificaciones.tiempoDeEnvio;

import domain.notificaciones.medioEnvio.MedioNotificacion;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PreferenciaEnvioNotificacion {

    MedioNotificacion medioNotificacion;

    Recepcion recepcionNotificacion;

    public PreferenciaEnvioNotificacion(MedioNotificacion medioNotificacion, Recepcion recepcionNotificacion) {
        this.medioNotificacion = medioNotificacion;
        this.recepcionNotificacion = recepcionNotificacion;
    }
}
