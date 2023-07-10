package tests.domain.notificaciones;

import domain.Localizacion.Localizacion;
import domain.comunidad.Comunidad;
import domain.entidades.Establecimiento;
import domain.incidentes.Incidente;
import domain.incidentes.IncidenteFactory;
import domain.notificaciones.medioEnvio.AdapterTwillio;
import domain.notificaciones.medioEnvio.AdapterWhatsapp;
import domain.notificaciones.medioEnvio.MedioNotificacion;
import domain.notificaciones.medioEnvio.WhatsApp;
import domain.notificaciones.tiempoDeEnvio.EnviarNotificacion;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import domain.notificaciones.tiempoDeEnvio.Recepcion;
import domain.registro.Contrasenia;
import domain.registro.Usuario;
import domain.services.georef.entities.Provincia;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.mockito.Mockito.*;
public class NotificacionesTest {

    @Test
    public void test01NotificadorCreaUnIncidente() {
        Provincia provincia = new Provincia();
        provincia.id = 10;

        Servicio banios = new Servicio("Baño", "Mujeres");
        Establecimiento macdonalds = new Establecimiento("McDonalds", provincia);
        PrestacionDeServicio servicioAfectado = new PrestacionDeServicio(banios, macdonalds);
        Contrasenia contrasenia = new Contrasenia("Juan1234");
        PreferenciaEnvioNotificacion preferencia = new PreferenciaEnvioNotificacion();
        preferencia.setMedioNotificacion(new WhatsApp());
        ArrayList<EnviarNotificacion> notificacionesSinEnviar = new ArrayList<>();
        preferencia.setRecepcionNotificacion(new Recepcion(ModoRecepcion.SINCRONICA));
        Usuario usuarioReportador = new Usuario("Juan", contrasenia, "jperez@gmail.com",preferencia);
        IncidenteFactory incidenteFactory = new IncidenteFactory();
        Comunidad comunidadAfectada = new Comunidad();
        Incidente incidente = incidenteFactory.crearIncidente(servicioAfectado, usuarioReportador,comunidadAfectada);
        

        assert(incidente.getEstado().equals("Abierto"));
    }
}
