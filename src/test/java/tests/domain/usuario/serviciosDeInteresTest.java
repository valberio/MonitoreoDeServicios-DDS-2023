package tests.domain.usuario;

import domain.entidades.Entidad;
import domain.entidades.Establecimiento;
import domain.notificaciones.medioEnvio.WhatsApp;
import domain.notificaciones.tiempoDeEnvio.ModoRecepcion;
import domain.notificaciones.tiempoDeEnvio.PreferenciaEnvioNotificacion;
import domain.registro.Contrasenia;
import domain.registro.Usuario;
import domain.services.georef.entities.Ubicacion;
import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class serviciosDeInteresTest {

    private Contrasenia unUsuarioContra = new Contrasenia(("buenasTardes2"));
    private PreferenciaEnvioNotificacion preferencia = new PreferenciaEnvioNotificacion(new WhatsApp(), ModoRecepcion.SINCRONICA);
    private Usuario unUsuario = new Usuario("marsoteras", unUsuarioContra, "marsoteras@gmail.com", preferencia);

    private Entidad lineaMitre = new Entidad("Linea Mitre");
    private Establecimiento Retiro = new Establecimiento("Estacion Retiro", new Ubicacion( -34.5833300, 58.3833300));
    private Establecimiento Tigre = new Establecimiento("Fin del recorrido linea Mitre", new Ubicacion(-34.4237,-58.5794));

    private Servicio escaleraMecanica = new Servicio("medio elevación", "lo usas para subir");
    private Servicio banioMujeres = new Servicio("banio", "banio para mujeres");

    PrestacionDeServicio brindarEscalera = new PrestacionDeServicio(escaleraMecanica, Retiro);
    PrestacionDeServicio brindarBanio = new PrestacionDeServicio(banioMujeres, Retiro);

    PrestacionDeServicio brindarBanioTigre = new PrestacionDeServicio(banioMujeres, Tigre);

    @BeforeEach

    public void instanciarUsuarioYEntidades() {

        unUsuario.agregarEntidadesDeInteres(lineaMitre);
        lineaMitre.agregarEstablecimientos(Retiro, Tigre);

        Retiro.brindarServicios(brindarEscalera, brindarBanio);
        Tigre.brindarServicios(brindarBanioTigre);

    }

    private void deshabilitarPrestaciones() {

        brindarBanioTigre.deshabilitar();
        brindarEscalera.deshabilitar();

    }
    @Test
    public void testObtenerServiciosDeInteresIncidentados(){

        this.deshabilitarPrestaciones();
        ArrayList<Servicio> listaEsperada = new ArrayList<Servicio>();

        listaEsperada.add(escaleraMecanica);
        listaEsperada.add(banioMujeres);


        Assertions.assertEquals(unUsuario.serviciosDeInteres(), listaEsperada);


    }

}
