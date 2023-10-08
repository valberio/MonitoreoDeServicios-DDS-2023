package models.entities.domain.services.fusionComunidades.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import models.entities.domain.comunidad.Comunidad;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PropuestaDeFusion {

    private ArrayList<Comunidad> comunidadesAFusionar = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaPropuesta = LocalDateTime.now();

    public Boolean masDeXMesesDePropuesta(Integer mes){
        return this.fechaPropuesta.plusMonths(mes).isBefore(LocalDateTime.now());
    }

    public void agregarComunidad(Comunidad comunidadModel){
        comunidadesAFusionar.add(comunidadModel);
    }
}
