package domain.notificaciones.medioEnvio;

import domain.notificaciones.Notificacion;
import domain.registro.Usuario;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class Mail implements  MedioNotificacion{

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    public void enviarNotificacionA(Usuario usuario, String texto) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario.getEmail(), usuario.getContrasenia().getContrasenia());
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario.getEmail()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail()));
        message.setSubject("Tenes notificaciones de incidentes que requieren tu atencion");
        message.setText(texto);

        Transport.send(message);
    }
}
