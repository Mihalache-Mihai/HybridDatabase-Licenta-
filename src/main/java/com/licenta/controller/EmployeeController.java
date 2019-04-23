package com.licenta.controller;

import com.licenta.models.Employee;
import com.licenta.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public Iterable<Employee> getCompanies() {
        return employeeRepository.findAll();
    }


    @PutMapping(value = "/{id}")
    public void editEmployee(@PathVariable long id, @RequestBody Employee employee) {
        if(employee.getRole().equals("ADMINISTRATOR")){

        }
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        Assert.notNull(existingEmployee, "Employee not found");
        if (employee.getCnp() != null) {
            existingEmployee.setCnp(employee.getCnp());
            log.info("Employee CNP updated successfully");
        }
        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
            log.info("Employee email updated successfully");
        }
        if (employee.getFirstName() != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            log.info("Employee first name updated successfully");
        }
        if (employee.getLastName() != null) {
            existingEmployee.setLastName(employee.getLastName());
            log.info("Employee last name updated successfully");
        }
        if (employee.getSalary() != null) {
            existingEmployee.setSalary(employee.getSalary());
            log.info("Employee salary updated successfully");
        }
        if(employee.getRole()!=null){
            existingEmployee.setRole(employee.getRole());
            log.info("Employee role updated successfully!");
        }
        employeeRepository.save(existingEmployee);
        log.info("Employee updated successfully!");
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeRepository.findById(id).ifPresent(employeeRepository::delete);
        log.info("Employee with id " + id + " deleted successfully!");
    }

}
