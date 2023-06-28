package domain.entidades;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import domain.config.Config;
import lombok.Getter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class cargaEntidadesyOrgDeControl {

    protected ArrayList<OrganismoDeControl> organismosRegistrados = new ArrayList<OrganismoDeControl>();

    public void cargarEntidadesYOrgDeControl() throws IOException, CsvValidationException {
        String archivo = Config.RUTA_CSV;

        try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
            String[] linea;

            while ((linea = reader.readNext()) != null) {
                //Formato CSV:
                //Organismo de Control, CUIT, Entidad Prestad ora

                if(organismoYaRegistrado(linea[1]))
                {
                    final String cuit = linea[1];

                    Optional<OrganismoDeControl> organismoEncontrado = organismosRegistrados.stream()
                            .filter(organismo -> organismo.getNombre().equals(nombreOrganismo))
                            .findFirst();

                    organismoEncontrado.get().AnadirPrestadoraControlada(new PrestadoraDeServicio(linea[1]));;

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

        return (cuitsOrganismosRegistrados.contains(CUIT));
    }

    

}

