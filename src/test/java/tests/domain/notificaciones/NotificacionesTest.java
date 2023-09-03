package tests.domain.notificaciones;

import domain.comunidad.Comunidad;
import domain.entidades.Establecimiento;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.incidentes.ReportadorDeIncidentes;
import domain.notificaciones.medioEnvio.Mail;
import domain.notificaciones.medioEnvio.WhatsApp;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
//import domain.notificaciones.tiempoDeEnvio.EnviarNotificacionAsincronica;
import domain.registro.Contrasenia;
import domain.registro.Registro;
import domain.registro.Usuario;
import domain.services.georef.entities.Ubicacion;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.time.LocalTime;
/*
public class NotificacionesTest {

    Contrasenia contrasenia = new Contrasenia("Juan1234");

    PreferenciaEnvioNotificacion preferencia = new PreferenciaEnvioNotificacion(new WhatsApp(),ModoRecepcion.SINCRONICA);

    Usuario usuarioJuan = new Usuario("juan",contrasenia,"jperez@gmail.com", preferencia);

    ReportadorDeIncidentes reportadorDeIncidentes = new ReportadorDeIncidentes();

    Servicio banios = new Servicio("Baño", "Mujeres");

    Establecimiento macdonalds = new Establecimiento("McDonalds", new Ubicacion(-34.5602699,-58.458387));

    PrestacionDeServicio servicioAfectado = new PrestacionDeServicio(banios, macdonalds);

    @Test
    public void seCreaUnIncidenteTest() throws MessagingException {

        Registro.getInstancia().registrarUsuario(usuarioJuan);

        Comunidad comunidadAfectada = new Comunidad();

        Incidente incidente = reportadorDeIncidentes.crearIncidente(servicioAfectado, usuarioJuan,comunidadAfectada, "");

        assert(incidente.getEstado().equals(EstadoIncidente.ACTIVO));
    }


    @Test

    public void encolarCorrectamenteTest() {

        Usuario usuarioReportador = new Usuario("anotherUsuario", contrasenia,"prueba", new PreferenciaEnvioNotificacion(new Mail(), new EnviarNotificacionAsincronica(ModoRecepcion.ASINCRONICA)));

        Registro.getInstancia().registrarUsuario(usuarioJuan);
        Registro.getInstancia().registrarUsuario(usuarioReportador);

        PreferenciaEnvioNotificacion pref = new PreferenciaEnvioNotificacion(new Mail(), ModoRecepcion.ASINCRONICA);

        usuarioJuan.setPreferencias(pref);

        usuarioJuan.configurarHorariosDisponibles(LocalTime.of(23,59));

        Comunidad comunidadAfectada = new Comunidad();

        comunidadAfectada.agregarUsuario(usuarioReportador, usuarioJuan);

        Incidente unIncidente = reportadorDeIncidentes.crearIncidente(servicioAfectado, usuarioReportador,comunidadAfectada, "");

        Incidente otroIncidente = reportadorDeIncidentes.crearIncidente(servicioAfectado,usuarioReportador, comunidadAfectada, "El mismo pero por otro usuario");

        Assertions.assertEquals(2, usuarioJuan.getModoRecepcion().getNotificacionesSinEnviar().size());



}
*/
