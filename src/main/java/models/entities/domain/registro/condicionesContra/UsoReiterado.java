package models.entities.domain.registro.condicionesContra;

import models.entities.domain.config.Config;
import models.entities.domain.registro.Contrasenia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class UsoReiterado implements Condicion{
    @Override
    public boolean cumpleCondicion(Contrasenia contrasenia) {

        try{
            this.esMuyUsada(contrasenia);
        }catch (ContraseniaUsoReiteradoException e){
            System.out.println(e.getMessage());
        }
        return false;

    }


    public boolean esMuyUsada (Contrasenia contrasenia) {
        File archivoContrasenias = new File(Config.RUTA_ARCHIVOS + Config.PEORES_CONTRASEÑAS_TXT);
        try {
            if(archivoContrasenias.exists()) {

                BufferedReader leerArchivo = new BufferedReader(new FileReader(archivoContrasenias));
                // LINEA LEIDA
                String lineaLeida;
                int lineasTotales=0;

                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                while((lineaLeida = leerArchivo.readLine()) != null) {
                    lineasTotales++;
                    String[] contraseniasUsadas = lineaLeida.split(" ");
                    for(int i = 0 ; i < contraseniasUsadas.length ; i++) {
                        if(contraseniasUsadas[i].equals(contrasenia.getContrasenia())) {
                            return true;
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

}