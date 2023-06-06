package domain.services.georef.entities;

import domain.Localizacion.Localizacion;

import java.io.IOException;

public class Provincia extends Localizacion {


    public Provincia obtenerse(int id) throws IOException {

        Provincia unaProvincia = this.servicioGeoref.listadoDeProvincias().provincias.get(id);
        return unaProvincia;
    }

    public String obtenerNombre(int id) throws IOException{
        return this.obtenerse(this.id).nombre;
    }
}
