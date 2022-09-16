package FormacionBackend.CRUDconValidacion.course.infraestructure.repository;

import FormacionBackend.CRUDconValidacion.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {}
