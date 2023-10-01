package tests.db;

import models.repositories.datos.RepositorioUsuarios;
import models.entities.domain.registro.Contrasenia;
import models.entities.domain.registro.Usuario;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RepositorioUsuariosTest implements SimplePersistenceTest {

    RepositorioUsuarios repo = RepositorioUsuarios.getInstance();

    Usuario persistente = new Usuario("micagobbi", "micagobbi@gmail.com", new Contrasenia("12345678/"));

    Usuario noPersistente = new Usuario("micagobbi", "micagobbi2@gmail.com", new Contrasenia("123456798/"));

    @Test
    public void deberiaEncontrarUnUsuarioDeNombreMica() {

        withTransaction(() -> {
            entityManager().persist(persistente);
        });

        Assertions.assertEquals(1, repo.filtrarPorNombre("micagobbi").size());
    }

    @Test
    public void noDeberiaEncontrarUnUsuarioSiNoExiste() {

        Assertions.assertEquals(0, repo.filtrarPorNombre("lourdesl").size());

    }

    @Test
    public void noPersisteUsuariosConNombreYaRegistrados() {
        persistente.setNombreDeUsuario("marsoteras");
        repo.agregarUsuario(persistente);

        noPersistente.setNombreDeUsuario("marsoteras");
        repo.agregarUsuario(noPersistente);

        Assertions.assertEquals(1, repo.filtrarPorNombre("marsoteras").size());

    }
}
