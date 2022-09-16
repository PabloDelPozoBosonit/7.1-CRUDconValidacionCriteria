package FormacionBackend.CRUDconValidacion.student.infraestructure.dtos;


import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseOutputDTO;


import FormacionBackend.CRUDconValidacion.student.domain.Student;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;


@Getter
public class StudentSimpleOutputDTO {

    Integer id_student;
    Integer numHoursWeek;
    String comments;
    String branch;

    Set<CourseOutputDTO> courses;



    public StudentSimpleOutputDTO(Student student) {
        this.id_student = student.getIdStudent();
        this.numHoursWeek = student.getNumHoursWeek();
        this.comments = student.getComments();
        this.branch = student.getBranch();
        this.courses = student.getCourses().stream().map(course -> new CourseOutputDTO(course)).collect(Collectors.toSet());

    }
}
