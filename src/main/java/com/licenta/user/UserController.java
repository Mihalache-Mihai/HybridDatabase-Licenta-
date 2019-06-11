package com.licenta.user;

import com.licenta.controller.EmployeeController;
import com.licenta.models.Employee;
import com.licenta.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeRepository employeeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserController(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public Employee signUp(@RequestBody Employee employee){
        employee.getCredentials().setPassword(bCryptPasswordEncoder.encode(employee.getCredentials().getPassword()));
        employee.getCredentials().setEmployee(employee);
        if(employeeRepository.findByCredentials_Username(employee.getCredentials().getUsername())!=null){
            return null;
        }
        employeeRepository.save(employee);

        log.info("Employee signed up successfully!");
        return employee;
    }


}
