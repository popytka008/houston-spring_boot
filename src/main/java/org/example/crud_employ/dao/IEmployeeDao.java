package org.example.crud_employ.dao;

import org.example.crud_employ.entity.Employee;

import java.util.List;

public interface IEmployeeDao {

    List<Employee> findAll();
    Employee findEmployeeById(int id);
    Employee save( Employee employee );
    void deleteEmployeeById(int id);
}
