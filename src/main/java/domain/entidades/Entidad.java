package domain.transporte;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;


@Getter
@Setter
public class Entidad {
    private String nombre;
    private ArrayList<Establecimiento> establecimientosAsociados; // Asumiendo que la lista esta ordenada, se podria de aqui extraer estacion origen y destino cuando corresponda


}
