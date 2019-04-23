package com.licenta.repository;

import com.licenta.models.Credentials;
import com.licenta.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByCredentials(Credentials credentials);
}
