package domain.transporte;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioPublico {
    private LineaDeTransporte lineaDeTransporte;
    private TipoDeTransporte tipoDeTransporte;

    private Boolean estaHabilitado = true;

    public ServicioPublico(LineaDeTransporte lineaDeTransporte, TipoDeTransporte tipoDeTransporte){
        this.lineaDeTransporte = lineaDeTransporte;
        this.tipoDeTransporte = tipoDeTransporte;
    }


}
