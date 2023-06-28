package domain.notificaciones.envio;

import domain.notificaciones.MedioNotificacion;
import domain.notificaciones.envio.ModoDeRecepcion;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PreferenciaEnvioNotificacion {

    MedioNotificacion medioNotificacion;

    ModoDeRecepcion recepcionNotificacion;

}
