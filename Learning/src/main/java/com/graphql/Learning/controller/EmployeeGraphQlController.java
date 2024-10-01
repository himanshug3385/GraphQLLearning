package com.graphql.Learning.controller;

import com.graphql.Learning.dao.EmployeeRepository;
import com.graphql.Learning.entity.Employee;
import com.graphql.Learning.entity.EmployeeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeGraphQlController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryMapping
    public List<Employee> findAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @QueryMapping
    public Employee findEmployeeByID(@Argument("id") int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    @MutationMapping
    public Employee createEmployee(@Argument int id, @Argument String name, @Argument String department, @Argument String email, @Argument int manager_id, @Argument int salary) {
        Employee emp = new Employee(id, name, department, manager_id, salary, email);
        return employeeRepository.save(emp);
    }

    @MutationMapping
    public Employee addEmployee(@Argument EmployeeInput emp) {
        return employeeRepository.save(new Employee(emp.id(), emp.name(), emp.department(), emp.manager_id(), emp.salary(), emp.email()));
    }

    @MutationMapping
    public Employee updateEmployee(@Argument int id, @Argument EmployeeInput emp) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) throw new RuntimeException("Employee not found");
        employee.setName(emp.name());
        employee.setSalary(emp.salary());
        employee.setManager_id(emp.manager_id());
        employee.setDepartment(emp.department());
        employee.setEmail(emp.email());
        employeeRepository.save(employee);
        return employee;
    }

    @MutationMapping
    public Employee deleteEmployee(@Argument int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employeeRepository.deleteById(id);
        return employee;
    }

}