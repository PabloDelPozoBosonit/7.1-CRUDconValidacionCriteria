package FormacionBackend.CRUDconValidacion.teacher.infraestructure.repository;

import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query(value ="SELECT *FROM TEACHER  WHERE ID_USER = ?1",nativeQuery = true)
    Teacher findByIdUser(Integer idUser);
}
