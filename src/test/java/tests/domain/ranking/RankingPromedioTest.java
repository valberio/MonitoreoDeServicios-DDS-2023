package tests.domain.ranking;

import datos.RepositorioUsuarios;
import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import domain.incidentes.Estado;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.incidentes.rankings.PromedioDeCierre;
import domain.incidentes.rankings.Ranking;
import domain.notificaciones.medioEnvio.WhatsApp;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

<<<<<<< Updated upstream
=======
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
>>>>>>> Stashed changes
public class RankingPromedioTest {

    @Test
    public void testRankingPorPromedioDeCierre() throws MessagingException {

        PromedioDeCierre rankingpromedioDeCierre = new PromedioDeCierre();

        PreferenciaEnvioNotificacion pref = new PreferenciaEnvioNotificacion(new WhatsApp(), ModoRecepcion.SINCRONICA);
        Usuario usuario = new Usuario("Pepita", null, null);
        usuario.setPreferencias(pref);
        RepositorioUsuarios.agregarUnUsuario(usuario);

        LocalDateTime fechaDeReporte = LocalDateTime.of(2023, 10, 7, 7, 0);


        Servicio baños = new Servicio("Baños", "Baños de hombres y mujeres");

        Establecimiento sedeLugano = new Establecimiento("Sede Lugano", null);
        Establecimiento sedeMedrano = new Establecimiento("Sede Medrano", null);

        Entidad UTN = new Entidad();
        UTN.setNombre("UTN");
        UTN.agregarEstablecimientos(sedeLugano, sedeMedrano);
        sedeLugano.setEntidad(UTN);
        sedeMedrano.setEntidad(UTN);
        usuario.agregarEntidadesDeInteres(UTN);

        PrestacionDeServicio prestacion = new PrestacionDeServicio(baños, sedeLugano);
        PrestacionDeServicio prestacion2 = new PrestacionDeServicio(baños, sedeMedrano);

        Incidente incidente1 = new Incidente(prestacion, null,  null, null);
        LocalDateTime tiempoAntes = LocalDateTime.of(2023, 10, 7, 7, 0);
        incidente1.setFechaReporte(fechaDeReporte);

        Incidente incidente2 = new Incidente(prestacion2, null, null, null);
        LocalDateTime tiempoDespues = LocalDateTime.of(2023, 10, 7, 8, 0);
        incidente2.setFechaReporte(fechaDeReporte);

        incidente1.cerrarse(usuario);
        EstadoIncidente estadoResuelto = new EstadoIncidente();
        estadoResuelto.setUsuarioResponsable(usuario);
        estadoResuelto.setFechaModificacion(tiempoAntes);
        estadoResuelto.setEstado(Estado.RESUELTO);
        incidente1.setEstado(estadoResuelto);
        incidente1.setFechaResolucion(tiempoAntes);

        incidente2.cerrarse(usuario);
        EstadoIncidente estadoResuelto2 = new EstadoIncidente();
        estadoResuelto2.setUsuarioResponsable(usuario);
        estadoResuelto2.setFechaModificacion(tiempoDespues);
        estadoResuelto2.setEstado(Estado.RESUELTO);
        incidente2.setEstado(estadoResuelto2);
        incidente2.setFechaResolucion(tiempoDespues);


        Establecimiento facultadIng = new Establecimiento("Facultad de Ingeniería", null);
        Entidad UBA = new Entidad();
        UBA.setNombre("UBA");
        UBA.agregarEstablecimientos(facultadIng);
        facultadIng.setEntidad(UBA);
        usuario.agregarEntidadesDeInteres(UBA);

        PrestacionDeServicio prestacion3 = new PrestacionDeServicio(baños, facultadIng);
        Incidente incidente3 = new Incidente(prestacion3, null,null, null);
        incidente3.setFechaReporte(fechaDeReporte);

        LocalDateTime tiempoMuchoDespues =  LocalDateTime.of(2024, 10, 7, 7, 0);
        incidente3.cerrarse(usuario);
        EstadoIncidente estadoResuelto3 = new EstadoIncidente();
        estadoResuelto3.setUsuarioResponsable(usuario);
        estadoResuelto3.setFechaModificacion(tiempoMuchoDespues);
        estadoResuelto3.setEstado(Estado.RESUELTO);
        incidente3.setEstado(estadoResuelto3);
        incidente3.setFechaResolucion(tiempoMuchoDespues);

        System.out.println(incidente3.obtenerEntidad().getNombre());
        ArrayList<Entidad> resultadoRanking = rankingpromedioDeCierre.generarRanking();

        resultadoRanking.forEach(resultado -> {System.out.println(resultado.getPromedioCierre()); System.out.println(resultado.getNombre());});

        List<Incidente> incUBA = new ArrayList<Incidente>();
        incUBA.add(incidente3);
        Map<Entidad, List<Incidente>> mapa = Map.of(UBA, incUBA);
        Map<Entidad, Double> mapaRanking = rankingpromedioDeCierre.generarPromedioCierrePorEntidad(mapa);
        System.out.println(mapaRanking.get(UBA));

}
}
<<<<<<< Updated upstream
=======
*/

public class RankingPromedioTest {

    @Test
    public void testRankingPorPromedioDeCierre() throws MessagingException {
        PromedioDeCierre rankingpromedioDeCierre = new PromedioDeCierre();

        Usuario usuario = new Usuario("Pepita", null, null);
        PreferenciaEnvioNotificacion pref = new PreferenciaEnvioNotificacion(new WhatsApp(), ModoRecepcion.SINCRONICA);
        usuario.setPreferencias(pref);
        RepositorioUsuarios.agregarUnUsuario(usuario);


        //todos los incidentes se reportan en la misma fecha
        LocalDateTime fechaDeReporte = LocalDateTime.of(2023, 10, 7, 0, 0);

        Servicio baños = new Servicio("Baños", "Baños de hombres y mujeres");

        Establecimiento sedeLugano = new Establecimiento("Sede Lugano", null);
        Establecimiento sedeMedrano = new Establecimiento("Sede Medrano", null);

        Entidad UTN = new Entidad();
        UTN.setNombre("UTN");
        UTN.agregarEstablecimientos(sedeLugano, sedeMedrano);
        sedeLugano.setEntidad(UTN);
        sedeMedrano.setEntidad(UTN);
        usuario.agregarEntidadesDeInteres(UTN);
        PrestacionDeServicio prestacion = new PrestacionDeServicio(baños, sedeLugano);
        PrestacionDeServicio prestacion2 = new PrestacionDeServicio(baños, sedeMedrano);

        Establecimiento facultadIng = new Establecimiento("Facultad de Ingeniería", null);
        Entidad entidadConMuyMalPromedio = new Entidad();
        entidadConMuyMalPromedio.setNombre("UBA");
        entidadConMuyMalPromedio.agregarEstablecimientos(facultadIng);
        facultadIng.setEntidad(entidadConMuyMalPromedio);
        usuario.agregarEntidadesDeInteres(entidadConMuyMalPromedio);
        PrestacionDeServicio prestacion3 = new PrestacionDeServicio(baños, facultadIng);


        Incidente incidente1 = new Incidente(prestacion, null,  null, null);
        LocalDateTime tiempoAntes = LocalDateTime.of(2023, 10, 7, 0, 0);
        incidente1.setFechaReporte(fechaDeReporte);

        Incidente incidente2 = new Incidente(prestacion2, null, null, null);
        LocalDateTime tiempoDespues = LocalDateTime.of(2023, 10, 7, 8, 0);
        incidente2.setFechaReporte(fechaDeReporte);

        Incidente incidente3 = new Incidente(prestacion3, null,null, null);
        incidente3.setFechaReporte(fechaDeReporte);
        LocalDateTime tiempoMuchoDespues =  LocalDateTime.of(2023, 12, 7, 7, 0);


        incidente1.cerrarse(usuario);
        incidente1.setFechaResolucion(tiempoAntes);
        incidente2.cerrarse(usuario);
        incidente2.setFechaResolucion(tiempoDespues);
        incidente3.cerrarse(usuario);
        incidente3.setFechaResolucion(tiempoMuchoDespues);

        ArrayList<Entidad> ranking = rankingpromedioDeCierre.generarRanking();

        ranking.forEach(resultado -> {System.out.println("Promedio en minutos:" + resultado.getPromedioCierre() + " Entidad:" + resultado.getNombre());});

        Assertions.assertEquals(entidadConMuyMalPromedio, ranking.get(0));

    }
}
>>>>>>> Stashed changes
