package FormacionBackend.CRUDconValidacion.course.infraestructure.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CourseInputDTO {


    private Integer idCourse;
    private String name;
    private String comments;
    private Date initialDate;
    private Date finishDate;
    private int idTeacher;

}
