package FormacionBackend.CRUDconValidacion.user.infraestructure.dtos;

import FormacionBackend.CRUDconValidacion.user.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserOutputDTO {


    private Integer idUser;
    private String user;
    private int age;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;



    public UserOutputDTO(User user) {

        this.idUser = user.getIdUser();
        this.user = user.getUser();
        this.age = user.getAge();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.companyEmail = user.getCompanyEmail();
        this.personalEmail = user.getPersonalEmail();
        this.city = user.getCity();
        this.active = user.isActive();
        this.createdDate = user.getCreatedDate();
        this.imagenUrl = user.getImagenUrl();
        this.terminationDate = user.getTerminationDate();
    }
}
