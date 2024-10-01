package com.graphql.Learning.entity;

public record EmployeeInput(int id, String name, String department,String email, int manager_id, int salary) {
}
