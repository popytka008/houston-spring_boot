package org.example.crud_employ.service;

import org.example.crud_employ.dao.EmployeeRepository;
import org.example.crud_employ.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeServiceImpl implements IEmployeeService {

    private EmployeeRepository repo;


    //-----------КОНСТРУКТОРЫ
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> findAll() {
        return this.repo.findAll();
    }

    @Override
    public Employee findById(int id) {
        var item = this.repo.findById( id );

        if ( item.isPresent() ){
            return item.get();
        }

        throw new RuntimeException(
                "Не правильная операция: Такой ID = " + id + " не подходит для выборки данных!!!"
        );
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return this.repo.save( employee );
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.repo.deleteById( id );
    }
}
