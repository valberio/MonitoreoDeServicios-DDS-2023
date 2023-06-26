package domain.notificaciones;

import datos.ArchivoIncidentes;
import domain.Localizacion.Localizacion;
import domain.incidente.Incidente;
import domain.registro.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NotificadorRevisiones {

    public void enviarSugerenciasRevisionA(Usuario usuario){
        Localizacion localizacionUsuario = usuario.getLocalizacion();
        List<Incidente> incidentesCercanos;
        incidentesCercanos = ArchivoIncidentes.getInstance().incidentes.stream()
                .filter(incidente -> incidente.getPrestacionDeServicio().getEstablecimiento().getUbicacionGeografica().
                                    estasCercaDe(localizacionUsuario)).
                                    collect(Collectors.toList());
        for (Incidente incidenteCercano : incidentesCercanos) {
            System.out.println(incidenteCercano.getPrestacionDeServicio().getServicio().descripcion);
        }
        System.out.println("Entre en el metodo");
    }
}
