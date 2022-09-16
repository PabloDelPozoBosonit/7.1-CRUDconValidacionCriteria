package FormacionBackend.CRUDconValidacion.user.infraestructure.dtos;

import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherOutputDTO;
import FormacionBackend.CRUDconValidacion.user.domain.User;
import lombok.Getter;

@Getter

public class UserTeacherOutputDTO extends UserOutputDTO {

    TeacherOutputDTO teacherOutputDTO;

    public UserTeacherOutputDTO(User user, Teacher teacher ) {
        super(user);

        this.teacherOutputDTO = new TeacherOutputDTO(teacher);
    }
}
