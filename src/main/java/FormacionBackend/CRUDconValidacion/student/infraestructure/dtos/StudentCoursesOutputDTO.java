package FormacionBackend.CRUDconValidacion.student.infraestructure.dtos;

import FormacionBackend.CRUDconValidacion.course.domain.Course;
import FormacionBackend.CRUDconValidacion.student.domain.Student;
import lombok.Data;

@Data
public class StudentCoursesOutputDTO {

    int studentId;
    int courseId;
    String courseName;
    Integer idTeacher;

    public StudentCoursesOutputDTO(Student student, Course course) {

        this.studentId = student.getIdStudent();
        this.courseId = course.getIdCourse();
        this.courseName = course.getName();
        this.idTeacher = course.getTeacher().getIdTeacher();


    }

}
