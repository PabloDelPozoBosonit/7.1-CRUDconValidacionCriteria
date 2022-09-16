package FormacionBackend.CRUDconValidacion.student.infraestructure.dtos;


import FormacionBackend.CRUDconValidacion.student.domain.Student;
import FormacionBackend.CRUDconValidacion.user.infraestructure.dtos.UserOutputDTO;
import lombok.Getter;




@Getter
public class StudentFullOutputDTO extends StudentSimpleOutputDTO {


    UserOutputDTO userOutputDTO;
    private Integer idTeacher;


    public StudentFullOutputDTO(Student student) {
        super(student);

        this.userOutputDTO = new UserOutputDTO(student.getUser());

        this.idTeacher = student.getTeacher().getIdTeacher();

    }

}
