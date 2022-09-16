package FormacionBackend.CRUDconValidacion.user.infraestructure.controllers;

import FormacionBackend.CRUDconValidacion.feign.IFeignServer;
import FormacionBackend.CRUDconValidacion.feign.IFeignUser;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherOutputDTO;
import FormacionBackend.CRUDconValidacion.user.domain.User;
import FormacionBackend.CRUDconValidacion.server.OutputDTO;
import FormacionBackend.CRUDconValidacion.user.infraestructure.dtos.UserInputDTO;
import FormacionBackend.CRUDconValidacion.user.infraestructure.dtos.UserOutputDTO;
import FormacionBackend.CRUDconValidacion.user.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;


@RequestMapping(value = "user")
@RestController
public class ControllerUser {

    @Autowired
    UserService usuarioService;

    @Autowired
    IFeignServer iFeignServer;

    @Autowired
    IFeignUser iFeignUser;


    /*
     *
     * Al ser Post, no es necesario indicarle el valor
     * Recibimos un JSON UserinputDTO
     * Se lo mandamos a la capa de servicio
     * */
    @PostMapping
    public UserOutputDTO insertUser(@RequestBody UserInputDTO userInputDTO) throws Exception {

        return usuarioService.createUser(userInputDTO);
    }


    //Recibimos el id y se lo pasamos a la capa bde servicio
    @GetMapping(value = "/obtener/{id}/{tipo}")
    public UserOutputDTO getUser(@PathVariable Integer id, @PathVariable String tipo) throws Exception {

        return usuarioService.getUser(id, tipo);
    }

    //Recibimos los datos a actualizar y el id y se lo pasamos a la capa bde servicio
    @PutMapping(value = "/actualizar/{id}")
    public UserOutputDTO updateUser(@RequestBody UserInputDTO userInputDTO, @PathVariable Integer id) throws Exception {

        return usuarioService.updateUser(userInputDTO, id);

    }


    @DeleteMapping(value = "/eliminar/{id}")
    public void deleteUser(@PathVariable Integer id){usuarioService.deleteUser(id);}


    @GetMapping(value = "/findByName/{name}")
    public List<User> findByName(@PathVariable String name) {
        return usuarioService.findByName(name);
    }


    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        return usuarioService.findAll();
    }


    //Probando RestTemplate
    //Haciendo uso de la clase server
    @GetMapping("/template/{code}")
    ResponseEntity<OutputDTO> callUsingRestTemplate(@PathVariable int code) {

        System.out.println("En el client RestTemplate. Antes de llamada a server devolveré: " + code);
        ResponseEntity<OutputDTO> rs = new RestTemplate().getForEntity("http://localhost:8080/server/" + code, OutputDTO.class);
        System.out.println("En el client RestTemplate. Despues  de llamada a server devolveré: " + code);
        return ResponseEntity.ok(rs.getBody());
    }


    //Probando feign
    //Lo mismo que con RestTemplate pero usando feign
    @GetMapping("/feign/{code}")
    ResponseEntity<OutputDTO> callUsingFeign(@PathVariable int code) {

        System.out.println("En el client. Antes de llamada a server devolveré: " + code);
        ResponseEntity<OutputDTO> respuesta = iFeignServer.callServer(code);
        System.out.println("En el client RestTemplate. Despues  de llamada a server devolveré: " + code);
        return respuesta;
    }


    /*Probando RestTemplate segun pedia el ejercicio

    En los endpoint de user, si llamamamos a este metodo, este llama a http://localhost:8080/teacher/obtener del controlador de teacher
    y obtiene el teachedr con el id pasado como parametro*/
    @GetMapping("teacher/obtener/template/{idTeacher}")
    ResponseEntity<TeacherOutputDTO> callTeacherFromUserUsingTemplate(@PathVariable int idTeacher) throws Exception {

        System.out.println("En el client RestTemplate");
        ResponseEntity<TeacherOutputDTO> rs = new RestTemplate().getForEntity("http://localhost:8080/teacher/obtener/" + idTeacher, TeacherOutputDTO.class);
        System.out.println("En el client RestTemplate. Despues  de llamada a server");

        return ResponseEntity.ok(rs.getBody());
    }


    /*Probando RestTemplate segun pedia el ejercicio

En los endpoint de user, si llamamamos a este metodo, este llama a http://localhost:8080/teacher/obtener del controlador de teacher
y obtiene el teachedr con el id pasado como parametro*/
    @GetMapping("teacher/obtener/feign/{idTeacher}")
    ResponseEntity<TeacherOutputDTO> callTeacherFromUserUsingFeign(@PathVariable int idTeacher) throws Exception {

        System.out.println("En el client");
        ResponseEntity<TeacherOutputDTO> rs = iFeignUser.callTeacherController(idTeacher);
        System.out.println("En el client. Despues  de llamada a server");

        return ResponseEntity.ok(rs.getBody());
    }


}
