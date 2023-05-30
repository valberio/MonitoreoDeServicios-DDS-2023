package domain.entidades;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class cargaEntidadesyEntesReguladores {

        public static void CargarEntitidadesYEntesReguladores() throws IOException, CsvValidationException {

            String archivo = "src/main/java/datos/entidades.csv";

            try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
                String[] linea;
                String[] linea2;

                while (((linea = reader.readNext()) != null) && ((linea2 = reader.readNext()) != null)) {

                    Entidad entidad = new Entidad();

                    entidad.setNombre(linea[0]);

                    entidad.setEnte(new EnteRegulador(linea2[0]));

                    System.out.println(entidad.getNombre());
                    System.out.println(entidad.getEnte().getNombre());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
}
}
