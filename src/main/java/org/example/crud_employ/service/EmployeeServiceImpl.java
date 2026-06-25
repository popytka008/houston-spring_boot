package org.example.crud_employ.service;

import org.example.crud_employ.dao.IEmployeeRepository;
import org.example.crud_employ.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository repo;


    //-----------КОНСТРУКТОРЫ
    @Autowired
    public EmployeeServiceImpl(IEmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> findAll() {
        return this.repo.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {

        var item = this.repo.findById(id);

        if ( item.isPresent()) return item.get();
        else throw new NoSuchElementException(
                String.format("Неудачный запрос БД. Работник по ID : %d не найден!", id )
        );
    }

    @Override
    public Employee save(Employee employee) {
        return this.repo.save( employee );
    }

    @Override
    public void deleteById(int id) {
        this.repo.deleteById( id );
    }
}
