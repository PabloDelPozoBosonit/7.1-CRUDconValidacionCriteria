package FormacionBackend.CRUDconValidacion.teacher.infraestructure.Controllers;

import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherInputDTO;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherOutputDTO;
import FormacionBackend.CRUDconValidacion.teacher.application.TeacherService;
import FormacionBackend.CRUDconValidacion.user.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "teacher")
@RestController
public class ControllerTeacher {


    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;


    //Al ser el unico Post no es necesario el value
    //Recibimos un inputTeacherDTO y se lo mandamos a la capa de servicio
    @PostMapping
    public TeacherOutputDTO insertTeacher(@RequestBody TeacherInputDTO teacherInputDTO) throws Exception {
        return teacherService.createTeacher(teacherInputDTO);

    }


    //Obtenemos el id y se lo pssamos a la capa de servicio
    @GetMapping(value = "/obtener/{id}")
    public TeacherOutputDTO getTeacher(@PathVariable Integer id) throws Exception {
        return teacherService.getTeacher(id);
    }


    //Obtenemos el id y le pasamos los nuevos datos y el id a la capa de servicio
    @PutMapping(value = "/actualizar/{id}")
    public TeacherOutputDTO updateTeacher(@RequestBody TeacherInputDTO teacherInputDTO, @PathVariable Integer id) throws  Exception{

        return teacherService.updateTeacher(teacherInputDTO, id);
    }


    @DeleteMapping(value = "/eliminar/{id}")
    public void deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
    }


    @GetMapping(value = "/findAll")
    public List<Teacher> findAll() {
        return teacherService.findAll();
    }


}
