package FormacionBackend.CRUDconValidacion.teacher.application;


import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherInputDTO;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherOutputDTO;

import java.util.List;

public interface TeacherService {

    TeacherOutputDTO createTeacher(TeacherInputDTO teacherInputDTO) throws Exception;
    TeacherOutputDTO updateTeacher(TeacherInputDTO teacherInputDTO, Integer id) throws Exception;
    TeacherOutputDTO getTeacher(Integer id) throws Exception;
    void deleteTeacher(Integer id);

    List<Teacher> findAll();


}
