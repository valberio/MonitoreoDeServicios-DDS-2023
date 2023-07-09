package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.registro.Usuario;
import com.twilio.Twilio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.http.auth.AUTH;

public class AdapterTwillio implements AdapterWhatsapp {

    public static final String ACCOUNT_SID = "AC39d46828c8b731cb87c7ad3631fce232";
    public static final String AUTH_TOKEN = "3896ad9fb5a70218ef1786e163f3923d";


    @Override
    public void enviarNotificacionA(Usuario usuario, Notificacion notificacion) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String telefonoDeEnvio = "5491138920468";
        String telefonoRecepcion = usuario.getNumeroTelefono();


        Message message = Message.creator(
                        new PhoneNumber(telefonoRecepcion),
                        new PhoneNumber(telefonoDeEnvio),
                        notificacion.getTexto())
                .create();
    }

}
