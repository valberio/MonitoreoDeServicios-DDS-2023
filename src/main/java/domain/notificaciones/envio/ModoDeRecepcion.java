package domain.notificaciones.envio;

import domain.notificaciones.Notificacion;

public interface ModoDeRecepcion {

    // Recepcion en forma asincronica o sincronica

    public void recibir(Notificacion notificacion);

}
