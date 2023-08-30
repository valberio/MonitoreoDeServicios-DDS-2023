package tests.domain.ranking;

import datos.RepositorioUsuarios;
import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import domain.incidentes.Incidente;
import domain.incidentes.rankings.Ranking;
import domain.notificaciones.medioEnvio.WhatsApp;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import domain.notificaciones.tiempoDeEnvio.Recepcion;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankingPromedioTest {

    @Test
    public void testRankingPorPromedioDeCierre() throws MessagingException {

        Ranking ranking = new Ranking();

        PreferenciaEnvioNotificacion pref = new PreferenciaEnvioNotificacion(new WhatsApp(), ModoRecepcion.SINCRONICA);
        Usuario usuario = new Usuario("Pepita", null, null, pref);
        RepositorioUsuarios.agregarUnUsuario(usuario);

        Servicio baños = new Servicio("Baños", "Baños de hombres y mujeres");

        Establecimiento sedeLugano = new Establecimiento("Sede Lugano", null);
        Establecimiento sedeMedrano = new Establecimiento("Sede Medrano", null);

        Entidad UTN = new Entidad("UTN");
        UTN.agregarEstablecimientos(sedeLugano, sedeMedrano);
        sedeLugano.setEntidad(UTN);
        sedeMedrano.setEntidad(UTN);

        PrestacionDeServicio prestacion = new PrestacionDeServicio(baños, sedeLugano);
        PrestacionDeServicio prestacion2 = new PrestacionDeServicio(baños, sedeMedrano);

        Incidente incidente1 = new Incidente(prestacion, null, LocalDateTime.of(2023, 10, 7, 7, 0), null, null);
        Incidente incidente2 = new Incidente(prestacion2, null, LocalDateTime.of(2023, 10, 7, 8, 0), null, null);

        incidente1.cerrarse();
        incidente2.cerrarse();

        Establecimiento facultadIng = new Establecimiento("Facultad de Ingeniería", null);
        Entidad UBA = new Entidad("UBA");
        UBA.agregarEstablecimientos(facultadIng);
        facultadIng.setEntidad(UBA);

        PrestacionDeServicio prestacion3 = new PrestacionDeServicio(baños, facultadIng);
        Incidente incidente3 = new Incidente(prestacion3, null, LocalDateTime.of(2022, 10, 7, 7, 0),null, null);
        incidente3.cerrarse();
        System.out.println(incidente3.obtenerEntidad().getNombre());
        ArrayList<Entidad> resultadoRanking = ranking.generarPorPromedioDeCierre();

        resultadoRanking.forEach(resultado -> {System.out.println(resultado.getPromedioCierre()); System.out.println(resultado.getNombre());});

        List<Incidente> incUBA = new ArrayList<Incidente>();
        incUBA.add(incidente3);
        Map<Entidad, List<Incidente>> mapa = Map.of(UBA, incUBA);
        Map<Entidad, Double> mapaRanking = ranking.generarPromedioCierrePorEntidad(mapa);
        System.out.println(mapaRanking.get(UBA));
    }
}
