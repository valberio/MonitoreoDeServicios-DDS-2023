package domain.entidades;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class cargaEntidadesyOrgDeControl {

    protected ArrayList<OrganismoDeControl> organismosRegistrados = new ArrayList<OrganismoDeControl>();

    public void cargarEntidadesYOrgDeControl() throws IOException, CsvValidationException {
        String archivo = "src/main/java/datos/entidades.csv";

        try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
            String[] linea;

            while ((linea = reader.readNext()) != null) {
                //Formato CSV:
                //Organismo de Control, Entidad Prestad ora

                if(organismoYaRegistrado(linea[0]))
                {
                    final String nombreOrganismo = linea[0];

                    Optional<OrganismoDeControl> organismoEncontrado = organismosRegistrados.stream()
                            .filter(organismo -> organismo.getNombre().equals(nombreOrganismo))
                            .findFirst();

                    organismoEncontrado.get().AnadirPrestadoraControlada(new PrestadoraDeServicio(linea[1]));
                }
                else {
                    OrganismoDeControl organismo = new OrganismoDeControl(linea[0]);
                    organismosRegistrados.add(organismo);
                    organismo.AnadirPrestadoraControlada(new PrestadoraDeServicio(linea[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean organismoYaRegistrado(String nombre){
        ArrayList<String> nombresOrganismosRegistrados = (ArrayList<String>) organismosRegistrados.stream()
                .map(OrganismoDeControl::getNombre)
                .collect(Collectors.toList());

        return (nombresOrganismosRegistrados.contains(nombre));
    }
}

