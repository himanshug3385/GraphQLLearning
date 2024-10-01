package com.graphql.Learning.controller;

import com.graphql.Learning.dao.EmployeeRepository;
import com.graphql.Learning.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    //find all employees
    @GetMapping("/api/v1/employees")
    public List<Employee> getAllEmployee(){
        return (List<Employee>) employeeRepository.findAll();
    }
    //create/add a new employee
    @PostMapping("/api/v1/createEmployee")
    public Employee createEmployee(@RequestBody Employee emp) {
        Employee employee = employeeRepository.save(emp);
        return employee;
    }
    @PutMapping("/api/v1/updateEmployee")
    public Employee updateEmployee(@RequestParam("id") int id, @RequestBody Employee employee){
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if(employee1.isEmpty())return employee;
        Employee res =employee1.get();
        res.setEmail(employee.getEmail());
        res.setDepartment(employee.getDepartment());
        res.setName(employee.getName());
        res.setSalary(employee.getSalary());
        res.setManager_id(employee.getManager_id());
        employeeRepository.save(res);
        return res;
    }
    @DeleteMapping("/api/v1/deleteEmployee")
    public Employee deleteEmployee(@RequestParam("id") int id){
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if(employee1.isEmpty())return null;
        Employee employee = employee1.get();
        employeeRepository.deleteById(id);
        return employee;
    }
}
