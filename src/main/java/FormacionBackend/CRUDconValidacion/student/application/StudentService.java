package FormacionBackend.CRUDconValidacion.student.application;


import FormacionBackend.CRUDconValidacion.exceptions.UnprocessableEntityException;
import FormacionBackend.CRUDconValidacion.student.domain.Student;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentCoursesOutputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentFullOutputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentInputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentSimpleOutputDTO;

import java.util.List;

public interface StudentService {

    StudentSimpleOutputDTO createStudent(StudentInputDTO studentInputDto) throws UnprocessableEntityException;

    StudentFullOutputDTO updateStudent(StudentInputDTO studentInputDto, Integer id) throws Exception;

    StudentSimpleOutputDTO getStudent(Integer id, String outputType) throws Exception;

    void deleteStudent(Integer id);

    List<Student> findAll();

    StudentCoursesOutputDTO associateCourse(Integer idStudent, Integer idCourse) throws Exception ;

    void disassociateCourse(Integer idStudent, Integer idCourse) throws  Exception;
}

