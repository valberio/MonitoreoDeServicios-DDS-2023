package domain.entidades;

import domain.Localizacion.Localizacion;
import domain.services.georef.entities.Provincia;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

@Getter
public class Entidad {
    private String nombre;
    private ArrayList<Establecimiento> establecimientosAsociados = new ArrayList<>(); // Asumiendo que la lista esta ordenada, se podria de aqui extraer estacion origen y destino cuando correspond

    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    public void agregarEstablecimientos(Establecimiento ... unEstablecimiento) {

        establecimientosAsociados.addAll(List.of(unEstablecimiento));

    }

    public Stream<Servicio> serviciosConIncidente() {

        return establecimientosAsociados.stream().flatMap(Establecimiento::obtenerServiciosIncidentados);
    }

    public ArrayList<Localizacion> dondeOpera() {

        return establecimientosAsociados.stream().map(establecimiento->establecimiento.getUbicacionGeografica()).collect(Collectors.toCollection(ArrayList::new));
    }

}




