package domain.transporte;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioPublico {
    private LineaDeTransporte lineaDeTransporte;
    private String tipoDeTransporte;

    private Boolean estaHabilitado;

}
