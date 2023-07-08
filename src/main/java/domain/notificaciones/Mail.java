package domain.notificaciones;

import domain.registro.Usuario;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

//Todo lo que es smtp host y port nose si vale la pena dejar honestamente

public class Mail {
    //private static final String SMTP_HOST = "your_smtp_host";
    //private static final String SMTP_PORT = "your_smtp_port";

    public static void enviarNotificacionA(Usuario usuario, Notificacion notificacion) throws MessagingException {
        Properties properties = new Properties();
        //properties.put("mail.smtp.host", SMTP_HOST);
        //properties.put("mail.smtp.port", SMTP_PORT);
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
        message.setSubject("Asunto de la notificación");
        message.setText(notificacion.getTexto());

        Transport.send(message);
    }
}

