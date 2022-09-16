package FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos;


import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentSimpleOutputDTO;

import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Set;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
public class TeacherOutputDTO {

    Integer idTeacher;

    String comments;

    String branch;

    //Lista de estudiantes asociados al profesor
    Set<StudentSimpleOutputDTO> students;


    public TeacherOutputDTO(Teacher teacher) {
        this.idTeacher = teacher.getIdTeacher();
        this.comments = teacher.getComments();
        this.branch = teacher.getBranch();
        this.students = teacher.getStudents().stream().map(student -> new StudentSimpleOutputDTO(student)).collect(Collectors.toSet());
    }
}
