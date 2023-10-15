package controllers;

import com.opencsv.exceptions.CsvValidationException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.UploadedFile;
import models.entities.domain.entidades.Entidad;
import models.entities.domain.entidades.cargaEntidadesyOrgDeControl;
import models.entities.domain.incidentes.Incidente;
import models.repositories.datos.RepositorioEntidades;
import server.utils.ICrudViewsHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;



public class CargaOrganismosYEntidadesController implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        context.render("admin/cargaMasivaDeDatos.hbs");
    }

    @Override
    public void show(Context context) {


    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

        UploadedFile file = context.uploadedFile("csvFile");

        if (file != null) {
            // Define the destination directory and file name
            String uploadDirectory = "../../resources/public";
            String fileName = file.filename();

            // Combine the directory and file name to create the full path
            Path destinationPath = Path.of(uploadDirectory, fileName);

            try {
                // Copy the uploaded file content to the destination path
                Files.copy(file.content(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                cargaEntidadesyOrgDeControl carga = new cargaEntidadesyOrgDeControl();
                try {
                    carga.cargarEntidadesYOrgDeControl(String.valueOf(destinationPath));
                } catch (CsvValidationException e) {
                    throw new RuntimeException(e);
                }

                context.result("File uploaded and saved successfully.");
            } catch (IOException e) {
                context.result("Error saving the file: " + e.getMessage());
            }
        } else {
            context.result("No file uploaded");
        }

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
