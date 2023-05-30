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

                while ((linea = reader.readNext()) != null) {

                    Entidad entidad = new Entidad();

                    entidad.setNombre(linea[0]);

                    linea2 = reader.readNext();
                    EnteRegulador ente = new EnteRegulador(linea2[0]);
                    entidad.setEnte(ente);

                    System.out.println(entidad.getNombre());
                    System.out.println(entidad.getEnte().getNombre());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
}
}
