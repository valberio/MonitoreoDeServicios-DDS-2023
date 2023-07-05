package domain.notificaciones.envio;

import domain.notificaciones.MedioNotificacion;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PreferenciaEnvioNotificacion {

    MedioNotificacion medioNotificacion;

    Recepcion recepcionNotificacion;

}
