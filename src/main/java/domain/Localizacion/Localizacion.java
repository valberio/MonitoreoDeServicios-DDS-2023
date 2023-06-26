package domain.Localizacion;

import domain.services.georef.ServicioGeoref;

import java.io.IOException;
import java.util.Objects;

public abstract class Localizacion {
    public int id;
    public String nombre;
    public ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();

    public abstract Localizacion obtenerse(int id) throws IOException;

    public abstract String obtenerNombre(int id) throws IOException;

    public Boolean estasCercaDe(Localizacion otraLocalizacion) {
        try {
            return Objects.equals(otraLocalizacion.obtenerNombre(otraLocalizacion.id), this.obtenerNombre(this.id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
