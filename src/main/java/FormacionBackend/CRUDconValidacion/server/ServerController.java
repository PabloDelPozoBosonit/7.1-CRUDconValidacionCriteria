package FormacionBackend.CRUDconValidacion.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
* Esta clase se ha creado para probar el funcionamiento de RestTemplate, desde el controlador de User se llama
* a este metodo que retorna un OutputDTO con la informacion de la respuesta hhttp code*/
@RestController
@RequestMapping("server")
public class ServerController {

    @GetMapping("{httpCode}")
    ResponseEntity<OutputDTO>getHttpCode(@PathVariable int httpCode){
        System.out.println("En server. Devolveré: " + httpCode);
        return ResponseEntity.status(httpCode).body(new OutputDTO(httpCode, "Devuelto por server"));
    }
}
