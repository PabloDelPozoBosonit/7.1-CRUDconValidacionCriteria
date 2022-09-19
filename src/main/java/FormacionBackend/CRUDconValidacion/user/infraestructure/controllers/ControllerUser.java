package FormacionBackend.CRUDconValidacion.user.infraestructure.controllers;

import FormacionBackend.CRUDconValidacion.feign.IFeignServer;
import FormacionBackend.CRUDconValidacion.feign.IFeignUser;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherOutputDTO;
import FormacionBackend.CRUDconValidacion.user.domain.User;
import FormacionBackend.CRUDconValidacion.server.OutputDTO;
import FormacionBackend.CRUDconValidacion.user.infraestructure.dtos.UserInputDTO;
import FormacionBackend.CRUDconValidacion.user.infraestructure.dtos.UserOutputDTO;
import FormacionBackend.CRUDconValidacion.user.application.UserService;
import FormacionBackend.CRUDconValidacion.user.infraestructure.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RequestMapping(value = "user")
@RestController
public class ControllerUser {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService usuarioService;

    @Autowired
    IFeignServer iFeignServer;

    @Autowired
    IFeignUser iFeignUser;

    @Autowired
    EntityManager em;

    public static final String GREATER_THAN = "greater";
    public static final String LESS_THAN = "less";
    public static final String EQUAL = "equal";


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
    public UserOutputDTO getUser(@PathVariable Integer id, @PathVariable(required = false) String tipo) throws Exception {

        return usuarioService.getUser(id, tipo);
    }

    //Recibimos los datos a actualizar y el id y se lo pasamos a la capa bde servicio
    @PutMapping(value = "/actualizar/{id}")
    public UserOutputDTO updateUser(@RequestBody UserInputDTO userInputDTO, @PathVariable Integer id) throws Exception {

        return usuarioService.updateUser(userInputDTO, id);

    }


    @DeleteMapping(value = "/eliminar/{id}")
    public void deleteUser(@PathVariable Integer id) {
        usuarioService.deleteUser(id);
    }


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


    //PROBANDO CRITERIA BUILDER

    //Los parametros id, name, surname y fecha son todos opcionales
    //En postman, los campos que hay que escribir en el requestParam son: idUser, name, surname y created(los del data.put(*****))
    @GetMapping("/get")
    public List<User> getData(@RequestParam(required = false) Integer idUser,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String surname,
                              @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date createdDate,
                              @RequestParam(required = false) String dateCondition)

    {

        HashMap<String, Object> data = new HashMap<>();

        if (idUser != null)
            data.put("idUser", idUser);

        if (name != null)
            data.put("name", name);

        if (surname != null)
            data.put("surname", surname);

        if (dateCondition == null)
            dateCondition = GREATER_THAN;

        if (!dateCondition.equals(GREATER_THAN) && !dateCondition.equals(LESS_THAN) && !dateCondition.equals(EQUAL))
            dateCondition = GREATER_THAN;

        if (createdDate != null) {

            data.put("created", createdDate);
            data.put("dateCondition", dateCondition);
        }

        return userRepository.getData(data);

    }


    //Seguimos con criteria pero de otro modo
    ////En postman, los campos que hay que escribir en el requestParam son: idUser, name, surname y createdDate(los del query.setParameter("*****", ****)) entrecomillados

    //Si escribimos localhost:8080/user/getQuery muestra todos los usuarios ordenadosd por id
    //Si escribimos localhost:8080/user/getQuery/name los ordena por nombre
    @GetMapping({"/getQuery","/getQuery/{orderBy}"})
    public List<User> getDataQuery(@RequestParam(required = false) Integer idUser,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String surname,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date createdDate,
                                   @RequestParam(required = false) String dateCondition,
                                   @PathVariable(value = "orderBy",required = false) String orderBy)


    {
        HashMap<String, Object> data = new HashMap<>();


        String sql = "select e from usuario e where 1=1";

        if (idUser != null)
            sql += " and e.id = :idUser";

        if (name != null)
            sql += " and e.name = :name";

        if (surname != null)
            sql += " and e.surname = :surname";

        if (orderBy != null) {
            if (orderBy.equals("name")) {
                sql += " order by e.name";
            }
        }

        String cond;

        if (dateCondition == null)
            dateCondition = GREATER_THAN;

        switch (dateCondition) {
            case GREATER_THAN:
                cond = ">";
                break;
            case LESS_THAN:
                cond = "<";
                break;
            default:
                cond = "=";
        }

        if (createdDate != null)
            sql += " and e.created " + cond + " :createdDate";

        TypedQuery<User> query = em.createQuery(sql, User.class);

        if (idUser != null)
            query.setParameter("idUser", idUser);

        if (name != null)
            query.setParameter("name", name);

        if (surname != null)
            query.setParameter("surname", surname);

        if (createdDate != null)
            query.setParameter("createdDate", createdDate);


        return query.getResultList();

    }


}
