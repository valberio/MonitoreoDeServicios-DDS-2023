package models.entities.domain.services.gradoDeConfianza;

import models.entities.domain.config.Config;
import models.entities.domain.services.georef.GeorefService;
import models.entities.domain.services.georef.entities.RespuestaAPI;
import models.entities.domain.services.georef.entities.Ubicacion;
import models.entities.domain.services.gradoDeConfianza.entities.GradoDeConfianzaComunidad;
import models.entities.domain.services.gradoDeConfianza.entities.GradoDeConfianzaDeUsuario;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class GradoDeConfianzaAPI {
    private static GradoDeConfianzaAPI instancia = null;
    private static final String urlAPI = Config.URL_APIGEOREF;
    private Retrofit retrofit;

    private GradoDeConfianzaAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

    /*public GradoDeConfianzaComunidad obtenerDetallesUbicacion(double lat, double lon) throws IOException {

        CalculadorGradoDeConfianzaService calculadorService = this.retrofit.create(CalculadorGradoDeConfianzaService.class);
        Call<RespuestaAPI> peticion = calculadorService.obtenerUbicacion(lat, lon);
        Response<RespuestaAPI> respuesta = peticion.execute();

        if (respuesta.isSuccessful()) {
            Ubicacion ubicacion = respuesta.body().getUbicacion();
            return ubicacion;
        } else {
            // Manejar el error si la respuesta no es exitosa
            throw new IOException("Error en la respuesta: " + respuesta.message());
        }

    }

    public GradoDeConfianzaDeUsuario*/
