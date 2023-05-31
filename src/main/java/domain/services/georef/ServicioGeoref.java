package domain.services.georef;

import domain.config.Config;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.ListadoDeDepartamentos;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoref {
    private static ServicioGeoref instancia = null;
    private static final String urlAPI = Config.URL_API;
    private Retrofit retrofit;

    private ServicioGeoref(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ServicioGeoref getInstancia(){
        if(instancia ==null) {
            instancia = new ServicioGeoref();
        }
        return instancia;
    }

    public ListadoDeProvincias listadoDeProvincias(int id) throws IOException {

        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArg = georefService.provincias(id);
        Response<ListadoDeProvincias> responseProvinciasArgs = requestProvinciasArg.execute(); //aca llamo a la api
        return responseProvinciasArgs.body(); //aca la matcheo con mi clase molde

    }

    public ListadoDeMunicipios listadoDeMunicipios(int id) throws IOException {

        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestMunicipiosDeProvincia = georefService.municipios(id); //hago uso del primer metodo q hice para municipios
        Response<ListadoDeMunicipios> responseMunicipiosDeProvincia = requestMunicipiosDeProvincia.execute(); //aca llamo a la api
        return responseMunicipiosDeProvincia.body(); //aca la matcheo con mi clase molde

    }


    public ListadoDeDepartamentos listadoDeDepartamentos(int id) throws IOException {

        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeDepartamentos> requestDepartamentos = georefService.departamentos(id); //hago uso del primer metodo q hice para deptos
        Response<ListadoDeDepartamentos> responseDepartamentos = requestDepartamentos.execute(); //aca llamo a la api
        return responseDepartamentos.body(); //aca la matcheo con mi clase molde

    }
}
