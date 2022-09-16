package FormacionBackend.CRUDconValidacion.teacher.application;


import FormacionBackend.CRUDconValidacion.exceptions.EntityNotFoundException;
import FormacionBackend.CRUDconValidacion.exceptions.UnprocessableEntityException;
import FormacionBackend.CRUDconValidacion.teacher.domain.Teacher;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherInputDTO;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherOutputDTO;
import FormacionBackend.CRUDconValidacion.teacher.infraestructure.repository.TeacherRepository;
import FormacionBackend.CRUDconValidacion.user.domain.User;
import FormacionBackend.CRUDconValidacion.user.infraestructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserRepository userRepository;


    /*
     *
     * Recibimos un teacherInputDTO
     * Creamos un nuevo teacher
     * usamos su metodo createTeacher pasandole el inputDTO
     * Obtenemos su usuario asociado buscando en el repositorio de los usuarios con ese id
     * lo guardamos en el repositorio de los teacher
     * y por ultimo lo retornamos
     *
     * */
    @Override
    public TeacherOutputDTO createTeacher(TeacherInputDTO teacherInputDTO) throws Exception {


       //Se comprueba que el idUserAssociate no es nulo, de serlo, se lanza excepcion:
        if(teacherInputDTO.getIdUserAssociate() == null)
            throw new UnprocessableEntityException("IdUserAssociate  can not be null", 422);

        User user = userRepository.findById(teacherInputDTO.getIdUserAssociate()).orElseThrow(() -> new EntityNotFoundException("The user does no exist", 404));


        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.createTeacher(teacherInputDTO);
        teacherRepository.save(teacher);
        return new TeacherOutputDTO(teacher);

    }



    /*
     * Creamos teacher y le asignamos el teacher del repositorio con el id pasado por parametro(ya tenemos aqui el teacher a actualizar)
     * Lo guardamos con los datos recibidos
     * le asignamos el User con el mismo id
     * le asignamos el teacher con el teacher pasado en el request Body
     * */
    @Override
    public TeacherOutputDTO updateTeacher(TeacherInputDTO teacherInputDTO, Integer id) throws Exception {


        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The teacher does no exist", 404));
        teacher.setIdTeacher(id);
        User user = userRepository.findById(teacherInputDTO.getIdUserAssociate()).orElseThrow(() -> new EntityNotFoundException("The user does no exist", 404));
        teacher.setUser(user);
        teacher.createTeacher(teacherInputDTO);
        teacherRepository.save(teacher);
        return new TeacherOutputDTO(teacher);


    }

    //Recibimos el id, lo buscamos en el repositorio y retornamos outpoutDTO
    @Override
    public TeacherOutputDTO getTeacher(Integer id) throws Exception {

        Optional<Teacher> teacherOpt = teacherRepository.findById(id);

        if(teacherOpt.isEmpty())
            throw new EntityNotFoundException("The teacher does no exist", 404);


        Teacher teacher = teacherOpt.get();
        return new TeacherOutputDTO(teacher);
    }

    @Override
    public void deleteTeacher(Integer id) {

        Optional<Teacher> teacherOpt = teacherRepository.findById(id);

        if(teacherOpt.isEmpty())
            throw new EntityNotFoundException("The teacher does no exist", 404);

        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
