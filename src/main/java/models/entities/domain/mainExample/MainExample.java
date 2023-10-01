package models.entities.domain.mainExample;

import models.entities.domain.entidades.Establecimiento;
import models.entities.domain.incidentes.Incidente;
import models.entities.domain.services.georef.entities.Ubicacion;
import models.entities.domain.servicios.PrestacionDeServicio;
import models.entities.domain.servicios.Servicio;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class MainExample implements WithSimplePersistenceUnit {

    Servicio ascensor = new Servicio("Ascensor", "Ascensor para discapacitados");
    Establecimiento sedeSanIgnacio = new Establecimiento("Sede San Ignacio", new Ubicacion(52,52));


    public static void main(String[] args) {
        new MainExample().start2();
    }

    public void start() {

        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();


        Establecimiento sedeLugano = new Establecimiento("Sede Lugano", new Ubicacion(2,2));
        Establecimiento sedeMedrano = new Establecimiento("Sede Medrano", new Ubicacion(1,1));

        Establecimiento facultadIng = new Establecimiento("Facultad de Ingeniería", new Ubicacion(23,23));

        entityManager().persist(ascensor);
        entityManager().persist(sedeLugano);
        entityManager().persist(sedeMedrano);


        PrestacionDeServicio prestacion = new PrestacionDeServicio(ascensor, sedeLugano);
        PrestacionDeServicio prestacion2 = new PrestacionDeServicio(ascensor, sedeMedrano);

        PrestacionDeServicio prestacion3 = new PrestacionDeServicio(ascensor, facultadIng);

        entityManager().persist(prestacion);
        entityManager().persist(prestacion2);
        entityManager().persist(prestacion3);





        tx.commit();

    }

    public void start2() {

            LocalDateTime fechaDeReporte = LocalDateTime.of(2023, 10, 7, 0, 0);

            entityManager().persist(sedeSanIgnacio);

            PrestacionDeServicio prestacion4 = new PrestacionDeServicio(ascensor, sedeSanIgnacio);
            Incidente incidente1 = new Incidente(prestacion4, null,  null, null);
            incidente1.setFechaReporte(fechaDeReporte);

            Incidente incidente2 = new Incidente(prestacion4, null, null, null);
            incidente2.setFechaReporte(fechaDeReporte);

            Incidente incidente3 = new Incidente(prestacion4, null,null, null);
            incidente3.setFechaReporte(fechaDeReporte);

            entityManager().persist(prestacion4);
            entityManager().persist(incidente1);
            entityManager().persist(incidente2);
            entityManager().persist(incidente3);

        }
    }

