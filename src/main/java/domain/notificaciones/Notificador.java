package domain.notificaciones;

import domain.registro.Usuario;
import domain.incidentes.Incidente;

import java.util.ArrayList;

public class Notificador {

    private static Notificador instancia = null;

    public static Notificador getInstancia(){
        if(instancia==null) {
            instancia = new Notificador();
        }

        return instancia;
    }


    public static void notificar(Incidente incidente) {

        ArrayList<Usuario> usuariosInteresados = incidente.obtenerUsuariosInteresados();
        notificarUsuarios(usuariosInteresados);

    }

    public static void notificarUsuarios(ArrayList<Usuario> usuarios){

    }
}
