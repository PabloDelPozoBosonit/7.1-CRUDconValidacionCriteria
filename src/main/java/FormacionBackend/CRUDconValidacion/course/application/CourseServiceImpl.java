package FormacionBackend.CRUDconValidacion.course.application;

import FormacionBackend.CRUDconValidacion.course.domain.Course;
import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseInputDTO;
import FormacionBackend.CRUDconValidacion.course.infraestructure.dtos.CourseOutputDTO;
import FormacionBackend.CRUDconValidacion.course.infraestructure.repository.CourseRepository;
import FormacionBackend.CRUDconValidacion.exceptions.EntityNotFoundException;
import FormacionBackend.CRUDconValidacion.exceptions.UnprocessableEntityException;
import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {


    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public CourseOutputDTO createCourse(CourseInputDTO courseInputDTO) throws Exception {

        Course course = new Course();

        course.setTeacher(teacherRepository.findById(courseInputDTO.getIdTeacher()).orElseThrow(() -> new EntityNotFoundException("The teacher does not exist", 404)));
        course.createCourse(courseInputDTO);
        courseRepository.save(course);
        return new CourseOutputDTO(course);
    }


    @Override
    public CourseOutputDTO updateCourse(CourseInputDTO courseInputDTO, Integer id) throws UnprocessableEntityException {

        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The course does not exist", 404));

        Teacher teacher = teacherRepository.findById(courseInputDTO.getIdTeacher()).orElseThrow(() -> new EntityNotFoundException("The teacher does not exist", 404));
        course.setIdCourse(id);
        course.setTeacher(teacher);
        course.createCourse(courseInputDTO);
        courseRepository.save(course);

        return new CourseOutputDTO(course);
    }


    @Override
    public CourseOutputDTO getCourse(Integer id) throws Exception {

        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The course does not exist", 404));


        return new CourseOutputDTO(course);

    }

    @Override
    public void deleteCourse(Integer id) throws EntityNotFoundException {

        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The course does not exist", 404));

        courseRepository.delete(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
