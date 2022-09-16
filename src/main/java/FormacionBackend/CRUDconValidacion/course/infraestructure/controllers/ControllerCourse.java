package FormacionBackend.CRUDconValidacion.course.infraestructure.controllers;

import FormacionBackend.CRUDconValidacion.course.domain.Course;
import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseInputDTO;
import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseOutputDTO;
import FormacionBackend.CRUDconValidacion.course.application.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="course")
public class ControllerCourse {

    @Autowired
    CourseService courseService;

    @PostMapping
    public CourseOutputDTO insertCourse(@RequestBody CourseInputDTO courseInputDTO) throws Exception {
        return courseService.createCourse(courseInputDTO);
    }

    @GetMapping(value = "obtener/{id}")
    public CourseOutputDTO getCourse(@PathVariable Integer id) throws  Exception {
        return courseService.getCourse(id);
    }

    @PutMapping(value ="actualizar/{id}")
    public CourseOutputDTO updateCourse(@RequestBody CourseInputDTO courseInputDTO,@PathVariable Integer id) throws  Exception {
        return courseService.updateCourse(courseInputDTO, id);
    }


    @DeleteMapping(value = "eliminar/{id}")
    public void deleteCourse(@PathVariable Integer id) throws Exception {
         courseService.deleteCourse(id);
    }

    @GetMapping(value = "/findAll")
    public List<Course> findAll() {
        return courseService.findAll();
    }

}


