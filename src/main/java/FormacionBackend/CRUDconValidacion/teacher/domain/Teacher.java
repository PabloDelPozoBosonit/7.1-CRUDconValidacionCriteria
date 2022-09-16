package FormacionBackend.CRUDconValidacion.teacher.domain;

import FormacionBackend.CRUDconValidacion.student.domain.Student;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherInputDTO;
import FormacionBackend.CRUDconValidacion.user.domain.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class Teacher{


    @GeneratedValue
    @Id
    private Integer idTeacher;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private User user;

    private String comments;

    private String branch;

    @OneToMany
    Set<Student> students = new HashSet<>();


    public void createTeacher(TeacherInputDTO teacherInputDTO) {

        this.comments = teacherInputDTO.getComments();
        this.branch = teacherInputDTO.getBranch();
    }


}
