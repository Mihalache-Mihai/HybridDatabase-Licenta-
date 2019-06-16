package com.licenta.controller;

import com.licenta.models.Credentials;
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

    @PostMapping("/add")
    public void populate(){
        Credentials c =new Credentials();

        String password ="Mock password";

        c.setPassword(password);
        for(int i=0;i<3000;i++){
            String username = "Mock username second"+i;
            c.setUsername(username);
            Employee e = new Employee();
            String firstName = "mockName"+i;
            String lastName = "mockLastName";
            String email = "mockMailSecondTry"+i+"@yahoo.com";
            String CNP = "1234555512990"+i;
            Integer salary = 1243;

            e.setCredentials(c);
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setEmail(email);
            e.setCnp(CNP);
            e.setSalary(salary);

            employeeRepository.save(e);
            log.info("Employee saved: "+ firstName);
        }
    }


    @PutMapping(value = "/{id}")
    public void editEmployee(@PathVariable long id, @RequestBody Employee employee) {

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

        employeeRepository.save(existingEmployee);
        log.info("Employee updated successfully!");
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {

        employeeRepository.findById(id).ifPresent(employeeRepository::delete);
        log.info("Employee with id " + id + " deleted successfully!");
    }

    @GetMapping("/findBy/{username}")
    public Employee findByUsername(@PathVariable  String username){
        Employee employee = new Employee();
        long start_time = System.nanoTime();
        employee= employeeRepository.findByCredentials_Username(username);
        long end_time = System.nanoTime();
        long duration = end_time-start_time;

        double elapsedTimeInSecond = (double) duration / 1_000_000_000;
        log.info("Time employee query is: "+ Double.toString(elapsedTimeInSecond));
        employee.setResponseTime(Double.toString(elapsedTimeInSecond));
        return employee;
    }

}
