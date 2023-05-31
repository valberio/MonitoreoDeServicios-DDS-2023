package domain.entidades;

import domain.Localizacion.Localizacion;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@Setter
public class Entidad {
    private String nombre;
    private ArrayList<Establecimiento> establecimientosAsociados; // Asumiendo que la lista esta ordenada, se podria de aqui extraer estacion origen y destino cuando correspond
    private EnteRegulador ente;

    private Localizacion localizacion;

    public Stream<Servicio> serviciosConIncidente() {

        return establecimientosAsociados.stream().flatMap(Establecimiento::obtenerServiciosIncidentados);
    }
}




