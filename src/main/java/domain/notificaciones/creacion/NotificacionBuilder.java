package domain.notificaciones.creacion;

import domain.config.Config;
import domain.incidentes.Incidente;
import domain.notificaciones.Notificacion;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;

public class NotificacionBuilder {

    private String texto;

    public Notificacion construirNotificacion(LocalDateTime hora) {

        return new Notificacion(texto, hora);
    }

    public void construirTexto(String texto) {

            this.texto = texto;

    }

    public void construirTexto(Incidente incidente, ContextoDeIncidente contexto){

        String rutaArchivo = Config.RUTA_ARCHIVOS + "textos_incidente.json";

        try {
            FileReader archivo = new FileReader(rutaArchivo);

            JSONObject json = new JSONObject(archivo);

            switch(contexto) {

                case CREACION -> this.texto = json.getString("CreacionDeIncidente") + incidente.getServicioAfectado().obtenerTextoRelevante();

                case SUGERENCIA_DE_REVISION -> this.texto = json.getString("SugerenciaDeRevision") + incidente.getServicioAfectado() + " en " + incidente.getLocalizacion();

                case CIERRE -> this.texto = json.getString("CierreDeIncidente") + incidente.getServicioAfectado().obtenerTextoRelevante() + "!";
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void editarTexto(String nuevoTexto) {

        this.texto = this.texto + " - " + nuevoTexto + "\n";
    }
}
