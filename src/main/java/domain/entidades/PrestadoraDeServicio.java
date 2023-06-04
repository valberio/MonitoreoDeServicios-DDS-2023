package domain.entidades;
import domain.entidades.Entidad;
import domain.registro.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PrestadoraDeServicio{

    private String nombre;
    private ArrayList<Entidad> entidades = new ArrayList<Entidad>();

    private Usuario usuarioDesignado;

    public PrestadoraDeServicio(String nombre){this.nombre = nombre;}

    public void anadirEntidad(Entidad entidad){entidades.add(entidad);}

}
