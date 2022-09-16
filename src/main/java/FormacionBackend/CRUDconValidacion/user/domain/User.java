package FormacionBackend.CRUDconValidacion.user.domain;


import FormacionBackend.CRUDconValidacion.user.infraestructure.dtos.UserInputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



@Getter
@Setter
@Entity(name ="usuario") //Indicamos a JPA que esta clase es una entidad para que la pueda utilizar en su entorno de persistencia.
//@Table(name = "usuario") //Con esta anotación le indicamos a JPA a qué tabla hace referencia esta entidad que estamos creando.


public class User {


    @GeneratedValue
    @Id
    private Integer idUser;

    @Column(name = "usuario")
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


    public  void createUser(UserInputDTO userInputDTO) {


        this.user = userInputDTO.getUser();
        this.age = userInputDTO.getAge();
        this.password = userInputDTO.getPassword();
        this.name = userInputDTO.getName();
        this.surname = userInputDTO.getSurname();
        this.companyEmail = userInputDTO.getCompanyEmail();
        this.personalEmail = userInputDTO.getPersonalEmail();
        this.city = userInputDTO.getCity();
        this.active = userInputDTO.isActive();
        this.createdDate = userInputDTO.getCreatedDate();
        this.imagenUrl = userInputDTO.getImagenUrl();
        this.terminationDate = userInputDTO.getTerminationDate();

    }

}




