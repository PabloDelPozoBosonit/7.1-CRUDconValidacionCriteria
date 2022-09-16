package FormacionBackend.CRUDconValidacion.student.application;

import FormacionBackend.CRUDconValidacion.course.domain.Course;
import FormacionBackend.CRUDconValidacion.course.infraestructure.repository.CourseRepository;
import FormacionBackend.CRUDconValidacion.exceptions.EntityNotFoundException;
import FormacionBackend.CRUDconValidacion.exceptions.UnprocessableEntityException;
import FormacionBackend.CRUDconValidacion.student.domain.Student;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentCoursesOutputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentFullOutputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentInputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.dtos.StudentSimpleOutputDTO;
import FormacionBackend.CRUDconValidacion.student.infraestructure.repository.StudentRepository;
import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.repository.TeacherRepository;
import FormacionBackend.CRUDconValidacion.user.domain.User;
import FormacionBackend.CRUDconValidacion.user.infraestructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;



    /*
     * Recibe el inputDTO
     * crea student de la clase Student
     * usa su metodo createStudent pasandole el InputDTO
     * establece el user buscando en UserRepository el mismo id que el del student
     * lo guarda en el repositorio
     * retorna el OutputDTO simple mandandole el objeto student
     * */

    @Override
    public StudentSimpleOutputDTO createStudent(StudentInputDTO studentInputDto) throws UnprocessableEntityException {


        //Obtenemos el teacher a traves del id pasado en el body de studentInputDTO
        Optional<Teacher> teacherOpt = teacherRepository.findById(studentInputDto.getIdTeacher());

        //Al student le establecemos su usuario
        Optional<User> userOpt = userRepository.findById(studentInputDto.getIdUser());

        if (userOpt.isEmpty()) {
            throw new UnprocessableEntityException("User field can not be null", 422);
        }

        if (teacherOpt.isEmpty()) {
            throw new UnprocessableEntityException("Teacher field can not be null", 422);
        }

        //Una vez que comprobamos que no son nulos, se crean y asignan

        //Creamos un nuevo student
        Student student = new Student();

        User user = userOpt.get();
        Teacher teacher = teacherOpt.get();

        student.setUser(user);

        student.setTeacher(teacher);

        //Y la rama del teacher
        student.setBranch(teacherRepository.findById(studentInputDto.getIdTeacher()).orElseThrow().getBranch());

        //Al teacher le añadimos el student a su Set<Student>
        teacher.getStudents().add(student);
        //Llamamos a metodo que asigna los valores del inputDTO  a la entidad Student
        student.createStudent(studentInputDto);
        //Y lo guardamos en el repositorio
        studentRepository.save(student);
        //Por ultimo retornamos un OutputDTO de student
        return new StudentSimpleOutputDTO(student);
    }


    /*
     * Creamos student y le asignamos el student del repositorio con el di pasado por parametro(ya tenemos aqui el student a actualizar)
     * Lo guardamos con los datos recibidos
     * le asignamos el User con el mismo id
     * le asignamos el teacher con el teacher pasado en el request Body
     * */
    @Override
    public StudentFullOutputDTO updateStudent(StudentInputDTO studentInputDto, Integer id) throws Exception {

        Student student = studentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("The student does not exist", 404));
        User user = userRepository.findById(studentInputDto.getIdUser()).orElseThrow(()->new EntityNotFoundException("The user does not exist", 404));
        Teacher teacher = teacherRepository.findById(studentInputDto.getIdTeacher()).orElseThrow(()->new EntityNotFoundException("The teacher does not exist", 404));

        student.setIdStudent(id);
        student.setUser(user);
        student.setTeacher(teacher);
        student.setBranch(teacherRepository.findById(studentInputDto.getIdTeacher()).orElseThrow().getBranch());
        student.createStudent(studentInputDto);
        studentRepository.save(student);
        return new StudentFullOutputDTO(student);

    }


    /*
     *
     * Recibe el id y el tipo de mensaje
     * Crea un objeto de ipo Student y le asigna el student del repositorio con el id pasado por parametro(Ya hemos obtenido el student buscado)
     * Comporbamos el mensaje, si es full, retornamos fullOutputDTO(datos basicos + user asociado + teacher asociado)
     * si no, retornamos el simple(datos basicos)
     * */

    @Override
    @Transactional
    public StudentSimpleOutputDTO getStudent(Integer id, String outputType) throws Exception {

        Optional<Student> studentOpt = studentRepository.findById(id);

        if (studentOpt.isEmpty())
            throw new EntityNotFoundException("The student does not exist", 404);

        Student student = studentOpt.get();


        if (outputType.equals("full")) {
            return new StudentFullOutputDTO(student);
        }
        return new StudentSimpleOutputDTO(student);
    }


    @Override
    public void deleteStudent(Integer id) {

        //Si el student no existe, generamos excepcion
        Student student = studentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("The student does no exist", 404));

        //Despues le quitamos dicho student a su profesor asociado para poder eliminarlo
        Teacher teacher = student.getTeacher();

        teacher.getStudents().remove(student);

        studentRepository.deleteById(student.getIdStudent());
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    @Override
    @Transactional
    public StudentCoursesOutputDTO associateCourse(Integer idStudent, Integer idCourse) throws Exception {

        Optional<Student> studentOpt = studentRepository.findById(idStudent);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);

        if (studentOpt.isEmpty())
            throw new EntityNotFoundException("The student does no exist", 404);

        if (courseOpt.isEmpty())
            throw new EntityNotFoundException("The course does no exist", 404);

        Student student = studentOpt.get();

        Course course = courseOpt.get();

        student.getCourses().add(course);

        studentRepository.save(student);

        return new StudentCoursesOutputDTO(student, course);

    }

    @Override
    public void disassociateCourse(Integer idStudent, Integer idCourse) throws Exception {

        Optional<Student> studentOpt = studentRepository.findById(idStudent);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);

        if (studentOpt.isEmpty())
            throw new EntityNotFoundException("The student does no exist", 404);

        if (courseOpt.isEmpty())
            throw new EntityNotFoundException("The course does no exist", 404);

        Student student = studentOpt.get();

        Course course = courseOpt.get();

        student.getCourses().remove(course);
        studentRepository.save(student);


    }


}

