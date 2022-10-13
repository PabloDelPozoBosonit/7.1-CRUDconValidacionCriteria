package FormacionBackend.CRUDconValidacion.user.infraestructure.repository;

import FormacionBackend.CRUDconValidacion.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.HashMap;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name);
    public List<User>getData(HashMap<String,Object> conditions);

    /*
    * La función getData recibirá un hashmap donde iremos poniendo las condiciones de busqueda, a si si queremos
    * buscar los usuarios cuyo código sea igual a 1, añadiremos una la llave `id` y el valor '1':
    *
    *   HashMap<String,Object> hm= new HashMap<>();
    *   hm.put("id",1);
    *
    * Si deseamos que el nombre sea Pablo, añadiriamos:
    *   HashMap<String,Object> hm= new HashMap<>();
    *   hm.put("name","Pablo");
    * */

}
