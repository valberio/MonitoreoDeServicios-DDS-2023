package domain.services.georef.entities;

import domain.Localizacion.Localizacion;

import java.io.IOException;

public class Provincia extends Localizacion {

    public String obtenerse() throws IOException {

        Provincia unaProvincia = this.servicioGeoref.listadoDeProvincias(id).provincias.get(0);
        return unaProvincia.nombre;
    }
}
