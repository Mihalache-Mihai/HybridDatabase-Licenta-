package com.licenta.repository;

import com.licenta.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
