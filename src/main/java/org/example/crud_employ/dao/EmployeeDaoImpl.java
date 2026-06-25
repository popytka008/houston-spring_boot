package org.example.crud_employ.dao;

import jakarta.persistence.EntityManager;
import org.example.crud_employ.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao{

    private EntityManager entityManager;

    //----КОНСТРУКТОРЫ
    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        var q = this.entityManager.createQuery("from Employee ");
        return q.getResultList();
    }

    @Override
    public Employee findEmployeeById(int id) {
        var employee = this.entityManager.find( Employee.class, id );
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        var newEmployee = this.entityManager.merge( employee );
        return newEmployee;
    }

    @Override
    public void deleteEmployeeById(int id) {

        this.entityManager.remove(
                this.findEmployeeById( id )
        );
    }
}
