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

            while ((linea = reader.readNext()) != null) {
                Entidad entidad = new Entidad();
                entidad.setNombre(linea[0]);

                if (linea.length > 1) {
                    EnteRegulador ente = new EnteRegulador(linea[1]);
                    entidad.setEnte(ente);
                }

                System.out.println(entidad.getNombre());
                System.out.println(entidad.getEnte() != null ? entidad.getEnte().getNombre() : "null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
------------------------------- SECCIÓN INTENTOS -------------------------------

--------- Primero ---------
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

--------- Segundo ---------
public static void CargarEntitidadesYEntesReguladores() throws IOException, CsvValidationException {
    String archivo = "src/main/java/datos/entidades.csv";

    try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
        String[] linea;

        while ((linea = reader.readNext()) != null) {
            Entidad entidad = new Entidad();
            entidad.setNombre(linea[0]);

            System.out.println(entidad.getNombre());

            if ((linea = reader.readNext()) != null) {
                EnteRegulador ente = new EnteRegulador(linea[0]);
                entidad.setEnte(ente);
                System.out.println(entidad.getEnte().getNombre());
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

--------- Tercero ---------
public static void CargarEntitidadesYEntesReguladores() throws IOException, CsvValidationException {
    String archivo = "src/main/java/datos/entidades.csv";

    try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
        String[] linea;
        String[] linea2 = null;

        while ((linea = reader.readNext()) != null) {
            Entidad entidad = new Entidad();
            entidad.setNombre(linea[0]);

            System.out.println(entidad.getNombre());

            if (linea2 == null) {
                linea2 = reader.readNext();
            }

            if (linea2 != null) {
                EnteRegulador ente = new EnteRegulador(linea2[0]);
                entidad.setEnte(ente);
                System.out.println(entidad.getEnte().getNombre());
                linea2 = null; // Reiniciar la variable línea2 para la próxima iteración
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

*/