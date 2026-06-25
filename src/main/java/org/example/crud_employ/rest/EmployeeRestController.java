package org.example.crud_employ.rest;

import org.example.crud_employ.dao.IEmployeeDao;
import org.example.crud_employ.entity.Employee;
import org.example.crud_employ.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private IEmployeeService service;

    //----ОНСТРУКТОРЫ
    @Autowired
    public EmployeeRestController(IEmployeeService service) {
        this.service = service;
    }


    //----МЕТОДЫ
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/employees/{idx}")
    public Employee getEmployee(@PathVariable(value = "idx") int id){

        this.catchEmplyeeIdError( id );

        return this.service.findEmployeeById( id );
    }

    public void catchEmplyeeIdError(int id) {

        if ( id < 0 )
            throw new RuntimeException(
                    "Индекс имеет недопустимое значение : " + id
            );
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee e ){
        return this.service.save( e );
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee e ){
        return this.service.save( e );
    }

    @DeleteMapping("/employees/{idx}")
    public String deleteEmployee(@PathVariable(value = "idx") int id){

        this.catchEmplyeeIdError( id );

        var e = this.service.findEmployeeById( id );
        this.service.deleteEmployeeById( id );

        return "Запись удалена: " + e;
    }
    //
//    private IEmployeeDao dao;
//
//    //----ОНСТРУКТОРЫ
//    @Autowired
//    public EmployeeRestController(IEmployeeDao dao) {
//        this.dao = dao;
//    }
//
//
//    //-- ОБЩИЕ МЕТОДЫ
//    @GetMapping("/employees")
//    public List<Employee> findAll(){
//        return this.dao.findAll();
//    }
}
