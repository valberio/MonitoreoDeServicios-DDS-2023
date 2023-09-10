package converters;

import domain.notificaciones.medioEnvio.Mail;
import domain.notificaciones.medioEnvio.MedioNotificacion;
import domain.notificaciones.medioEnvio.WhatsApp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.print.attribute.Attribute;

@Converter(autoApply = true)
public class MedioNotificacionAttributeConverter implements AttributeConverter<MedioNotificacion,String> {
    @Override
    public String convertToDatabaseColumn(MedioNotificacion medioNotificacion) {

        String s = "";

        String name = medioNotificacion.getClass().getName();

        switch (name) {
            case "WhatsApp" -> s = "WhatsApp";
            case "Mail" -> s = "Mail";
        }
        return s;
    }

    @Override
    public MedioNotificacion convertToEntityAttribute(String s) {

        MedioNotificacion medio;

       switch(s) {
           case "WhatsApp":
               medio = new WhatsApp();
               break;
           case "Mail":
               medio = new Mail();
               break;
           default:
               medio = null;
       }
       return medio;
    }
}
