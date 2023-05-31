package domain.Localizacion;

import domain.services.georef.ServicioGeoref;

import java.io.IOException;

public abstract class Localizacion {
    public int id;
    public String nombre;

    protected ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();
    public abstract String obtenerse() throws IOException;
}
