package FormacionBackend.CRUDconValidacion.course.application;


import FormacionBackend.CRUDconValidacion.course.domain.Course;
import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseInputDTO;
import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseOutputDTO;

import java.util.List;

public interface CourseService {

    CourseOutputDTO createCourse(CourseInputDTO courseInputDTO) throws Exception;

    CourseOutputDTO updateCourse(CourseInputDTO courseInputDTO, Integer id) throws Exception;

    CourseOutputDTO getCourse(Integer id) throws Exception;

    void deleteCourse(Integer id) throws  Exception;

    List<Course> findAll();
}
