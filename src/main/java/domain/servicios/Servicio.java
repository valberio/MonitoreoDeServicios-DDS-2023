package domain.servicios;


import lombok.Getter;

import java.util.Collection;

@Getter
public class Servicio {

        private String tipoDeServicio;
        public String descripcion;


        public Servicio(String tipoDeServicio, String descripcion) {
                this.tipoDeServicio = tipoDeServicio;
                this.descripcion = descripcion;
        }

        public void modificarDescripcion(String nuevaDescripcion){
                this.descripcion = nuevaDescripcion;
        }

}
