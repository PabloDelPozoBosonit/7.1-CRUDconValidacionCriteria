package FormacionBackend.CRUDconValidacion.student.infraestructure.dtos;

import lombok.Data;


@Data
public class StudentInputDTO {

    private int idStudent;

    //Id del usuario asociado
    int idUser;
    //Id de su teacher
    int idTeacher;
    Integer numHoursWeek;
    String comments;
    //Set<Course> id_course;
}
