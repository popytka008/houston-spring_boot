package org.example.crud_employ.service;

import org.example.crud_employ.dao.IEmployeeDao;
import org.example.crud_employ.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeDao dao;


    //-----------КОНСТРУКТОРЫ
    @Autowired
    public EmployeeServiceImpl(IEmployeeDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Employee> findAll() {
        return this.dao.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return this.dao.findEmployeeById( id );
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.dao.save( employee );
    }

    @Override
    @Transactional
    public void deleteEmployeeById(int id) {
        this.dao.deleteEmployeeById( id );
    }
}
