package domain.services.georef.entities;

import domain.Localizacion.Localizacion;

import java.io.IOException;
import java.util.ArrayList;

public class Departamento extends Localizacion {
    public String obtenerse() throws IOException {

        Departamento unDepartamento = this.servicioGeoref.listadoDeDepartamentos(id).departamentos.get(0);
        return unDepartamento.nombre;

    }

}
