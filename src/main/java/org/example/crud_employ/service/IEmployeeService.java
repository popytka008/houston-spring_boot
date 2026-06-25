package org.example.crud_employ.service;

import org.example.crud_employ.entity.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> findAll();
    Employee findEmployeeById(int id);
    Employee save( Employee employee );
    void deleteById(int id);
}
