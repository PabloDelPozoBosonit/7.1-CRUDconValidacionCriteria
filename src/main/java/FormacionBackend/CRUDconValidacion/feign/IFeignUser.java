package FormacionBackend.CRUDconValidacion.feign;

import FormacionBackend.CRUDconValidacion.teacher.infraestructure.dtos.TeacherOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignUserTeacher", url = "http://localhost:8081/")
public interface IFeignUser {

    @GetMapping("teacher/obtener/{idTeacher}")
    ResponseEntity<TeacherOutputDTO> callTeacherController(@PathVariable int idTeacher);

}
