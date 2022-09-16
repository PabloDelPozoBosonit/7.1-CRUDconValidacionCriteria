package FormacionBackend.CRUDconValidacion.user.infraestructure.dtos;


import FormacionBackend.CRUDconValidacion.student.domain.Student;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentSimpleOutputDTO;
import FormacionBackend.CRUDconValidacion.user.domain.User;
import lombok.Getter;

@Getter
public class UserStudentOutputDTO extends UserOutputDTO {

    StudentSimpleOutputDTO studentSimpleOutputDTO;

    public UserStudentOutputDTO(User user, Student student) {

        super(user);
        this.studentSimpleOutputDTO = new StudentSimpleOutputDTO(student);
    }
}
