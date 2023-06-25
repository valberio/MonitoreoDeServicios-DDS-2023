package domain.services.georef.entities;

import domain.Localizacion.Localizacion;

import java.io.IOException;

public class Municipio extends Localizacion {

    private ListadoDeDepartamentos departamentos;

    public Municipio obtenerse(int id) throws IOException {

       Municipio unMunicipio = this.servicioGeoref.listadoDeMunicipios(id).municipios.get(0);
       return unMunicipio;
    }

    @Override
    public String obtenerNombre(int id) throws IOException {
        return this.obtenerse(id).nombre;
    }
}
