package tests.domain.notificaciones;
import domain.comunidad.Comunidad;
import domain.entidades.Establecimiento;
import domain.incidentes.Incidente;
import domain.notificaciones.Notificacion;
import domain.notificaciones.Notificador;
import domain.notificaciones.medioEnvio.MedioNotificacion;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import domain.notificaciones.tiempoDeEnvio.Recepcion;
import domain.registro.Contrasenia;
import domain.registro.Usuario;
import domain.Localizacion.Localizacion;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotificadorTests {
    // Usuario
    private MedioNotificacion medio = new MedioNotificacion() {
        @Override
        public void enviarNotificacionA(Usuario usuario, Notificacion notificacion) {

        }
    };
    private Contrasenia contraCarla = new Contrasenia("takataka123!!");
    private PreferenciaEnvioNotificacion pref = new PreferenciaEnvioNotificacion(medio, new Recepcion(ModoRecepcion.ASINCRONICA)); //what the
    private Usuario usuarioTest = new Usuario("Carla Luna", contraCarla, "carlaluna@gmail.com", pref);

    //Incidente1
    private Servicio servicio1 = new Servicio("baño", "baño de mujeres");
    private Localizacion localizacion1 = new Localizacion() {
        @Override
        public Localizacion obtenerse(int id) throws IOException {
            return null;
        }
        @Override
        public String obtenerNombre(int id) throws IOException {
            return null;
        }
    };
    private Establecimiento establecimiento1 = new Establecimiento("La Boca", localizacion1);
    private PrestacionDeServicio prestacion1 = new PrestacionDeServicio(servicio1, establecimiento1);
    private LocalDateTime date1 = LocalDateTime.of(2023, 7, 10, 15, 30);
    private Comunidad comunidad1 = new Comunidad(); //what the
    private Incidente incidente1 = new Incidente(prestacion1, usuarioTest, date1, comunidad1);

    //Incidente2

    //private PrestacionDeServicio prestacion2 = new PrestacionDeServicio(servicio2, establecimiento2);
    //private Incidente incidente2 = new Incidente();

    @Test
    public void testEncolar () {

        //notificar incidente 1
        //notificar incidente 2

        //equals cant encolada de notif = 2
    }
}
