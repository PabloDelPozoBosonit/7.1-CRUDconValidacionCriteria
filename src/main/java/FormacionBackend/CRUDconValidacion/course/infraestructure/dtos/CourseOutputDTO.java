package FormacionBackend.CRUDconValidacion.course.infraestructure.dtos;

import FormacionBackend.CRUDconValidacion.course.domain.Course;
import lombok.Getter;


import java.util.Date;

@Getter

public class CourseOutputDTO {


    private Integer idCourse;
    private String name;
    private String comments;
    private Date initialDate;
    private Date finishDate;
    private Integer  idTeacher;


    public CourseOutputDTO(Course course) {
        this.idCourse = course.getIdCourse();
        this.name = course.getName();
        this.comments = course.getComments();
        this.initialDate = course.getInitialDate();
        this.finishDate = course.getFinishDate();
        this.idTeacher = course.getTeacher().getIdTeacher();

    }
}
