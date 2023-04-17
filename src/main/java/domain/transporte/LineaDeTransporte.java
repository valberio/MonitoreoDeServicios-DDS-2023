package domain.transporte;

import domain.servicios.Servicio;

import java.util.List;

public class LineaDeTransporte {
    private String nombre;
    private Estacion estacionOrigen;
    private Estacion estacionDestino;
    private List<Estacion> recorrido;

}
