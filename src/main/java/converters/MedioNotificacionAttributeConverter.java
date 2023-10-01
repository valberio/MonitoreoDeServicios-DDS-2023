package converters;

import models.entities.domain.notificaciones.medioEnvio.Mail;
import models.entities.domain.notificaciones.medioEnvio.MedioNotificacion;
import models.entities.domain.notificaciones.medioEnvio.WhatsApp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
