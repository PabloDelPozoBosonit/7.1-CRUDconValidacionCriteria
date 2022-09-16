package FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos;


import lombok.Data;

@Data
public class TeacherInputDTO {

    private Integer idTeacher;

    //Id del usuariuo asociado
    private Integer idUserAssociate;
    private String comments;
    private String branch;
}
