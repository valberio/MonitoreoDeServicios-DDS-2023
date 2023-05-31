package domain;

import domain.services.georef.ServicioGeoref;
import domain.services.georef.entities.ListadoDeMunicipios;
import domain.services.georef.entities.ListadoDeProvincias;
import domain.services.georef.entities.Municipio;
import domain.services.georef.entities.Provincia;

import java.io.IOException;
import java.util.Scanner;

/*public class EjemploDeUsoApi {

    public static void main(String[] args) throws IOException{
        ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();

        System.out.println("Seleccione una de las siguientes provincias, ingresando su id");
        ListadoDeProvincias listadoDeProvincias = servicioGeoref.listadoDeProvincias();

        listadoDeProvincias.provincias.sort((p1, p2)-> p1.id >= p2.id? 1 : -1);

        for(Provincia unaProvincia: listadoDeProvincias.provincias){
            System.out.println(unaProvincia.id + ")" + unaProvincia.nombre);
        }

        Scanner entradaScanner = new Scanner(System.in);
        int idProvinciaElegida = Integer.parseInt(entradaScanner.nextLine());
        ListadoDeMunicipios municipios= servicioGeoref.listadoDeMunicipiosDeProvincia(idProvinciaElegida);

        for(Municipio municipio: municipios.municipios){
            System.out.println(municipio.nombre);
        }



    }

}*/
