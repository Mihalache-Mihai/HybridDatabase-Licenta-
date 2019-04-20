package com.licenta.user;

import com.licenta.models.Employee;
import com.licenta.repository.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private EmployeeRepository employeeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserController(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody Employee employee){
        employee.getCredentials().setPassword(bCryptPasswordEncoder.encode(employee.getCredentials().getPassword()));
        employeeRepository.save(employee);
    }
    //eu acum ce dubiu mai am, cand fac register merge pe /users/sign up
    // bun si ..la e/employee fac numa update delete da pai m-ai ingropat :))
    //da corect, as putea
    // daca am timp, da da
}
