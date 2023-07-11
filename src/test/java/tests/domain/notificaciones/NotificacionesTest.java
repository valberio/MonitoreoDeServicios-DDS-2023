package tests.domain.notificaciones;

import domain.comunidad.Comunidad;
import domain.entidades.Establecimiento;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import domain.incidentes.ReportadorDeIncidentes;
import domain.notificaciones.medioEnvio.WhatsApp;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import domain.notificaciones.tiempoDeEnvio.Recepcion;
import domain.registro.Contrasenia;
import domain.registro.Registro;
import domain.registro.Usuario;
import domain.services.georef.entities.Provincia;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Test;

public class NotificacionesTest {

    @Test
    public void test01SeCreaUnIncidente() {
        Provincia provincia = new Provincia();
        provincia.id = 10;

        Servicio banios = new Servicio("Baño", "Mujeres");

        Establecimiento macdonalds = new Establecimiento("McDonalds", provincia);

        PrestacionDeServicio servicioAfectado = new PrestacionDeServicio(banios, macdonalds);

        Contrasenia contrasenia = new Contrasenia("Juan1234");

        PreferenciaEnvioNotificacion preferencia = new PreferenciaEnvioNotificacion(new WhatsApp(),new Recepcion(ModoRecepcion.SINCRONICA));

        Usuario usuarioReportador = new Usuario("juan",contrasenia,"jperez@gmail.com", preferencia);

        Registro.getInstancia().registrarUsuario(usuarioReportador);

        ReportadorDeIncidentes reportadorDeIncidentes = new ReportadorDeIncidentes();

        Comunidad comunidadAfectada = new Comunidad();

        Incidente incidente = reportadorDeIncidentes.crearIncidente(servicioAfectado, usuarioReportador,comunidadAfectada, "");
        

        assert(incidente.getEstado().equals(EstadoIncidente.ACTIVO));
    }
}