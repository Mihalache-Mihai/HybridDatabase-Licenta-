package com.licenta.repository;

import com.licenta.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "employee")
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    //@RestResource(path = "my-todos/me")
    //@Query("select t from Todo t where t.ownerId = ?1")
    //List<Todo> findMyTodos(String nume);
}
