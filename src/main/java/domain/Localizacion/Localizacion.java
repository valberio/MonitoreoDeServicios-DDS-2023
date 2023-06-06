package domain.Localizacion;

import domain.services.georef.ServicioGeoref;

import java.io.IOException;

public abstract class Localizacion {
    public int id;
    public String nombre;
    public ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();

    public abstract Localizacion obtenerse(int id) throws IOException;

    public abstract String obtenerNombre(int id) throws IOException;

}
