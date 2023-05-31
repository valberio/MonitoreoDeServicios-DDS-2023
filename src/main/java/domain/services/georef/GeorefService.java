package domain.services.georef;

import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.ListadoDeDepartamentos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("campos") int idProvincia);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idMunicipio);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("campos") int idDepartamento);



}
