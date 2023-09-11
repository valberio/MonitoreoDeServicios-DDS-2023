package datos;

import domain.entidades.PrestadoraDeServicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class RepositorioPrestadorasDeServicio implements WithSimplePersistenceUnit {

    private static RepositorioPrestadorasDeServicio instancia = null;

    public static RepositorioPrestadorasDeServicio getInstance(){
        if (instancia == null) {instancia = new RepositorioPrestadorasDeServicio(); }
        return instancia;
    }

    public void agregarPrestadoraDeServicio(PrestadoraDeServicio prestadora) {
        entityManager().persist(prestadora);
    }

    public void eliminarPrestadora(PrestadoraDeServicio prestadora) {
        entityManager().remove(prestadora);
    }
}
