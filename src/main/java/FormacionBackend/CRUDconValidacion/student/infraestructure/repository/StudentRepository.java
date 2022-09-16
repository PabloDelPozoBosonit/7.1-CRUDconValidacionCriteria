package FormacionBackend.CRUDconValidacion.student.infraestructure.repository;

import FormacionBackend.CRUDconValidacion.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value ="SELECT * FROM STUDENT  WHERE ID_USER = ?1",nativeQuery = true)
    Student findByIdUser(Integer idUser);
}
