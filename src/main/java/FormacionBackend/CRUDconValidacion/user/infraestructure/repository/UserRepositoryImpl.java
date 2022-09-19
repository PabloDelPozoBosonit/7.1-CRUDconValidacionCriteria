package FormacionBackend.CRUDconValidacion.user.infraestructure.repository;


import FormacionBackend.CRUDconValidacion.user.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static FormacionBackend.CRUDconValidacion.user.infraestructure.controllers.ControllerUser.*;


public class UserRepositoryImpl {

   /* Lo primero es inyectar una referencia al objeto EDntityManager con la etiqueta @PersistanceContextEn la función sobre EntityManager crearemos un objeto CriteriaBuilder y sobre este objeto creamos un CriteriaQuery
    donde iremos  poniendo las diferentes condiciones de nuestra Query
    Para poder buscar las columnas sobre las que queremos realizar la consulta necesitaremos un objeto Root,
    que crearemnos a partir del abnterior objeto CriteriaQuery*/
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getData(HashMap<String,Object> conditions) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User>root = query.from(User.class);

        /*
        * Ahora creamos una lista de objeto Predicate. En esta lista irán todos los Predicate que nos son si
        no las condicines de nuestra query
        *
        * Utilizando lambdas y Streams para hacer el codigo mas limpio y sencillo, vamos recorriendo el HashMap
        * y añadiendo a la lista de Predicates las condicoones definidas.
        *
        * Partiendo del objeto CriteriaQuery se ira llamando a la función deseada según el criterio a aplicar.
        *  De esta manera, si queremos establecer como condición que un campo sea igual a un valor llamaremos a la función equal(),
        *  pasando como primer parámetro la Expresion que hace referencia al campo de la entidad, y después el valor deseado.
        *  El objeto Expresion se creara simplemente cogiendo del objeto Root anteriormente definido, el nombre de la columna
        *  sobre el que se establecerá la condición
        *
        * Si deseamos añadir una condición donde un campo sea como a un texto introducido se llamara a la función like().
        *  En caso de que deseemos que el campo tenga un valor superior al introducido se usara greaterThan() y así sucesivamente.
        *
        * Si el campo es de tipo Date, es necesario especificar el tipo de dato del campo como se muestra en el código root.get(field), pues de otra manera no sabrá parsear correctamente la fecha.

        *Resaltar que el nombre del campo es el definido en nuestra entity que lógicamente no tiene porque ser el de la columna
        *  en la base de datos. Por ejemplo, el campo de fecha en la entity del proyecto de ejemplo, esta creada con las siguientes
        *  sentencias:
        * */
        List<Predicate>predicates = new ArrayList<>();

        conditions.forEach((field,value) ->
        {
            switch (field)
            {
                case "idUser":
                    predicates.add(cb.equal (root.get(field), (Integer)value));
                    break;

                case "name":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;

                case "surname":
                    predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
                    break;
                case "created":
                    String dateCondition=(String) conditions.get("dateCondition");
                    switch (dateCondition)
                    {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThan(root.<Date>get(field),(Date)value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThan(root.<Date>get(field),(Date)value));
                            break;
                        case EQUAL:
                            predicates.add(cb.equal(root.<Date>get(field),(Date)value));
                            break;
                    }
                    break;
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }
}


