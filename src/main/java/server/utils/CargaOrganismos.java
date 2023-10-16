package server.utils;

import models.entities.domain.entidades.Entidad;
import models.entities.domain.entidades.Establecimiento;
import models.entities.domain.entidades.OrganismoDeControl;
import models.entities.domain.entidades.PrestadoraDeServicio;
import models.entities.domain.services.georef.entities.Ubicacion;
import models.repositories.datos.RepositorioEntidades;
import models.repositories.datos.RepositorioEstablecimientos;
import models.repositories.datos.RepositorioOrganismosDeControl;
import models.repositories.datos.RepositorioPrestadorasDeServicio;
import server.Server;

public class CargaOrganismos {
    public static void main(String[] args){

        RepositorioOrganismosDeControl repoOrganismos = new  RepositorioOrganismosDeControl();
        RepositorioPrestadorasDeServicio repoPrestadoras = new RepositorioPrestadorasDeServicio();
        RepositorioEntidades repositorioEntidades =new RepositorioEntidades();
        RepositorioEstablecimientos repositorioEstablecimientos = new RepositorioEstablecimientos();

        OrganismoDeControl organismo =  new OrganismoDeControl();

        organismo.setNombre("CNRT");
        organismo.setCUIT("30715255703");

        repoOrganismos.guardar(organismo);

        PrestadoraDeServicio prestadora = new PrestadoraDeServicio();

        prestadora.setNombre("Trenes Argentinos");

        prestadora.setOrganismoDeControl(organismo);

        repoPrestadoras.guardar(prestadora);

        organismo.aniadirPrestadoraControlada(prestadora);

        repoPrestadoras.actualizar(prestadora);

        repoOrganismos.actualizar(organismo);

        Entidad entidad1 = new Entidad();

        entidad1.setNombre("Linea Mitre");

        Entidad entidad2 = new Entidad();
        entidad2.setNombre("Linea Sarmiento");

        repositorioEntidades.guardar(entidad1);
        repositorioEntidades.guardar(entidad2);

        Establecimiento retiro = new Establecimiento("Retiro", new Ubicacion(-34.5927,-58.3786));
        Establecimiento tigre = new Establecimiento("Tigre", new Ubicacion(-34.4255,-58.5704));

        repositorioEstablecimientos.guardar(retiro);
        repositorioEstablecimientos.guardar(tigre);

        Establecimiento once = new Establecimiento("Estacion Once", new Ubicacion( -34.6097,-58.4083  ));

        Establecimiento liniers = new Establecimiento("Estacion Liniers", new Ubicacion( -34.6432, -58.5199));

        repositorioEstablecimientos.guardar(once);
        repositorioEstablecimientos.guardar(liniers);

        entidad1.agregarEstablecimientos(retiro,tigre);
        entidad2.agregarEstablecimientos(once, liniers);

        repositorioEntidades.actualizar(entidad1);
        repositorioEntidades.actualizar(entidad2);

        prestadora.aniadirEntidad(entidad1);
        prestadora.aniadirEntidad(entidad2);

        repoPrestadoras.actualizar(prestadora);


    }

}
