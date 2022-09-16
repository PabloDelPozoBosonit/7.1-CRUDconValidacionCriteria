package FormacionBackend.CRUDconValidacion.student.domain;

import FormacionBackend.CRUDconValidacion.course.domain.Course;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentInputDTO;
import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import FormacionBackend.CRUDconValidacion.user.domain.User;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.*;

@Setter
@Getter
@Entity //Indicamos a JPA que esta clase es una entidad para que la pueda utilizar en su entorno de persistencia.
//@Table(name = "student") //Con esta anotación le indicamos a JPA a qué tabla hace referencia esta entidad que estamos creando.

public class Student  {


    @Id
    @GeneratedValue
    Integer idStudent;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    User user;


    String comments;

    Integer numHoursWeek;


    @ManyToOne
    @JoinColumn(name = "idTeacher")
    Teacher teacher;

    String branch;



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "STUDENT_COURSE",
    joinColumns = @JoinColumn(name = "idStudent"),
    inverseJoinColumns = @JoinColumn(name = "idCourse"))
    private Set<Course> courses = new HashSet<>();


    public void createStudent(StudentInputDTO studentInputDto) {

        this.comments = studentInputDto.getComments();
        this.numHoursWeek = studentInputDto.getNumHoursWeek();

    }

}
