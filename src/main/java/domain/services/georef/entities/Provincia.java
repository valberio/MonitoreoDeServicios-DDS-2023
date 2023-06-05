package domain.services.georef.entities;

import domain.Localizacion.Localizacion;

import java.io.IOException;

public class Provincia extends Localizacion {

    public Localizacion obtenerse(int id) throws IOException {

        Provincia unaProvincia = this.servicioGeoref.listadoDeProvincias(id).provincias.get(0);
        return unaProvincia;
    }

    public String obtenerNombre(int id) throws IOException{
        return this.obtenerse(this.id).nombre;
    }
}
