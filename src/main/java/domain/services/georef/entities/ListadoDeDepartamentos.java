package domain.services.georef.entities;
import java.util.List;
public class ListadoDeDepartamentos {

    public int cantidad;

    public List<Departamento>departamentos;

    private class Parametro {
        public List<String> campos;
        public List<String> provincia;

    }
}
