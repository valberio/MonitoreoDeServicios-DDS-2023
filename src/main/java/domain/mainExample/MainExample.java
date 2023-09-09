package domain.mainExample;

import domain.registro.Contrasenia;
import domain.registro.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;

public class MainExample implements WithSimplePersistenceUnit {
    public static void main(String[] args) {
        new MainExample().start();
    }

    public void start() {

        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        Usuario mica = new Usuario();
        mica.setNombreDeUsuario("micagobbi");
        mica.setEmail("micaelagobbi@gmail.com");
        mica.setContrasenia(new Contrasenia("1234567677"));
        entityManager().persist(mica); // hago persistible a dani
        tx.commit();

    }
}
