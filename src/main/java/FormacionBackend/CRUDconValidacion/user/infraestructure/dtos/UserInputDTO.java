package FormacionBackend.CRUDconValidacion.user.infraestructure.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class UserInputDTO {


    private Integer idUser;
    private String user;
    private int age;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;

}
