package domain.servicios;
import domain.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="servicio")
public class Servicio extends Persistente {
        
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
