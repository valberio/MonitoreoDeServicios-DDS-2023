package domain.entidades;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import datos.RepositorioEntidadesyOrganismos;
import domain.config.Config;
import lombok.Getter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Getter
public class cargaEntidadesyOrgDeControl {

    protected ArrayList<OrganismoDeControl> organismosRegistrados = new ArrayList<>();

    public void cargarEntidadesYOrgDeControl() throws CsvValidationException {

        RepositorioEntidadesyOrganismos repositorio = RepositorioEntidadesyOrganismos.getInstance();
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
                            .filter(organismo -> organismo.getCUIT().equals(cuit))
                            .findFirst();

                    organismoEncontrado.get().aniadirPrestadoraControlada(new PrestadoraDeServicio());

                }
                else {
                    OrganismoDeControl organismo = new OrganismoDeControl();
                    organismo.setNombre(linea[0]);
                    organismo.setCUIT(linea[1]);
                    organismosRegistrados.add(organismo);

                    PrestadoraDeServicio prestadora = new PrestadoraDeServicio();
                    prestadora.setNombre(linea[2]);
                    organismo.aniadirPrestadoraControlada(prestadora);

                    repositorio.guardarOrganismoDeControl(organismo);
                    repositorio.guardarPrestadoraDeServicio(prestadora);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean organismoYaRegistrado(String CUIT){
        ArrayList<String> cuitsOrganismosRegistrados = (ArrayList<String>) organismosRegistrados.stream()
                .map(OrganismoDeControl::getCUIT)
                .toList();

        return (cuitsOrganismosRegistrados.contains(CUIT));
    }
}