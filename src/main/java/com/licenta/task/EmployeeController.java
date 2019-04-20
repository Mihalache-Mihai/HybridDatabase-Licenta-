package com.licenta.task;

import com.licenta.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeRepository employeeRepository;


}
