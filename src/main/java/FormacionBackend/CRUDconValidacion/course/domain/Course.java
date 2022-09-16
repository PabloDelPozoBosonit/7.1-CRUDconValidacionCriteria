package FormacionBackend.CRUDconValidacion.course.domain;

import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseInputDTO;
import FormacionBackend.CRUDconValidacion.student.domain.Student;
import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Course {

    @GeneratedValue
    @Id
    private Integer idCourse;
    private String name;

    private String comments;
    private Date initialDate;
    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();


    public void createCourse(CourseInputDTO courseInputDTO) {

        this.name = courseInputDTO.getName();;
        this.comments = courseInputDTO.getComments();;
        this.initialDate = courseInputDTO.getInitialDate();
        this.finishDate = courseInputDTO.getFinishDate();
    }
}
