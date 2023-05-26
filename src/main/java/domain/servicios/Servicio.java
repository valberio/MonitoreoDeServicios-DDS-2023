package domain.servicios;


import lombok.Getter;

@Getter
public class Servicio{

        private String tipoDeServicio;
        private String descripcion;
        private Boolean estaHabilitado;

        public Servicio(String tipoDeServicio, String descripcion) {
                this.tipoDeServicio = tipoDeServicio;
                this.descripcion = descripcion;
                this.estaHabilitado = true;
        }

        public void modificarDescripcion(String nuevaDescripcion){
                this.descripcion = nuevaDescripcion;
        }

        public void habilitar(){ estaHabilitado = true;}

        public void deshabilitar(){ estaHabilitado = false; }
}
