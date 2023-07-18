package domain.services.georef;

import domain.services.georef.entities.RespuestaAPI;
import domain.services.georef.entities.Ubicacion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {

    @GET("ubicacion")
    Call<RespuestaAPI> obtenerUbicacion(@Query("lat") double latitud, @Query("lon") double longitud);
}
