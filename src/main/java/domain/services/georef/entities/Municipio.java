package domain.services.georef.entities;

import domain.Localizacion.Localizacion;

import java.io.IOException;

public class Municipio extends Localizacion {
    public String obtenerse() throws IOException {

       Municipio unMunicipio = this.servicioGeoref.listadoDeMunicipios(id).municipios.get(0);
       return unMunicipio.nombre;
    }
}
