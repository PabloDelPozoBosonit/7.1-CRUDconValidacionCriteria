package FormacionBackend.CRUDconValidacion.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*Este DTO se creo para probar el uso de RestTemplate, muestra un mensaje y el httpCode*/
@Getter
@Setter
@NoArgsConstructor
public class OutputDTO {

    private int httpCode;
    private String mensaje;

    public OutputDTO(int httpCode, String mensaje){
        this.httpCode = httpCode;
        this.mensaje = mensaje;
    }

}
