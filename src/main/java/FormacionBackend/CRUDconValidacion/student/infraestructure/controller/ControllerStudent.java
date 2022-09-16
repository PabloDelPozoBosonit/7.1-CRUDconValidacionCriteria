package FormacionBackend.CRUDconValidacion.student.infraestructure.controller;

import FormacionBackend.CRUDconValidacion.student.application.StudentService;
import FormacionBackend.CRUDconValidacion.student.domain.Student;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentCoursesOutputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentFullOutputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentInputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentSimpleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "student")
@RestController
public class ControllerStudent {

    @Autowired
    StudentService studentService;





    //No necesario darle valor, si es Post, se da por entendido que es insertar un Student
    //Al llamar al metodo, llamamos la clase de servicio de student pasandole el inputDTO recibido en JSON por Post
    @PostMapping
    public StudentSimpleOutputDTO insertStudent(@RequestBody StudentInputDTO studentInputDTO) throws Exception
    {

         return studentService.createStudent(studentInputDTO);

    }


    //Al recibir el id y el outputType(simple | full) se lo mandamos a servicio de Student
    @GetMapping(value="/obtener/{id}/{outputType}")
    public StudentSimpleOutputDTO getStudent(@PathVariable Integer id, @PathVariable  String outputType) throws Exception {

       return studentService.getStudent(id, outputType);

    }


    //Recibimos por put el id y en el body el cuerpo a actualizar
    @PutMapping(value="/actualizar/{id}")
    public StudentFullOutputDTO updateStudent(@RequestBody StudentInputDTO studentInputDto, @PathVariable Integer id) throws  Exception{

        return studentService.updateStudent(studentInputDto, id);

    }


    //Recibimos id, y se lo mandamos al servicio
    @DeleteMapping(value = "/eliminar/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }


    @GetMapping(value = "/findAll")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @PostMapping(value = "/asociarAsignatura/{idStudent}/{idCourse}")
    public StudentCoursesOutputDTO associateCourse(@PathVariable Integer idStudent, @PathVariable Integer idCourse) throws Exception  {
            return studentService.associateCourse(idStudent, idCourse);
    }

    @PostMapping(value = "/deleteCourseFromStudent/{idStudent}/{idCourse}")
    public void disassociateCourse(@PathVariable Integer idStudent, @PathVariable Integer idCourse) throws Exception  {
        studentService.disassociateCourse(idStudent,idCourse);
    }


}
