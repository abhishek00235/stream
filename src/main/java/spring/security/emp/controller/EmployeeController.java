package spring.security.emp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.emp.Entity.EmployeeEntity;
import spring.security.emp.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("api/employee")
    public ResponseEntity<List<EmployeeEntity>> getEmployee(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }
}
