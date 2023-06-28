package domain.incidente;

import datos.ArchivoIncidentes;
import domain.registro.Usuario;
import domain.servicios.PrestacionDeServicio;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Incidente {

    public PrestacionDeServicio prestacionDeServicio;
    public Usuario usuarioReportador;
    public Time fechaReporte;
    public Time fechaResolucion;

    public Incidente(PrestacionDeServicio prestacionDeServicio, Usuario usuarioReportador, Time fechaReporte)
    {
        this.prestacionDeServicio = prestacionDeServicio;
        this.usuarioReportador = usuarioReportador;
        this.fechaReporte = fechaReporte;

        ArchivoIncidentes archivo = ArchivoIncidentes.getInstance();
        archivo.guardarIncidente(this);

    }
}
