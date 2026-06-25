package org.example.crud_employ.rest;

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
    //SELECT *
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return this.service.findAll();
    }

    //SELECT 1 WHERE ID=<id>
    @GetMapping("/employees/{idx}")
    public Employee getEmployee(@PathVariable(value = "idx") int id){

        this.catchEmplyeeIdError( id );

        return this.service.findById( id );
    }

    public void catchEmplyeeIdError(int id) {

        if ( id < 0 )
            throw new RuntimeException(
                    "Индекс имеет недопустимое значение : " + id
            );
    }
    //INSERT 1
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee e ){
        return this.service.save( e );
    }
    //UPDATE 1 WHERE ID=<id>
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee e ){
        return this.service.save( e );
    }
    //DELETE 1 WHERE ID=<id>
    @DeleteMapping("/employees/{idx}")
    public String deleteEmployee(@PathVariable(value = "idx") int id){

        this.catchEmplyeeIdError( id );

        var e = this.service.findById( id );
        this.service.deleteById( id );

        return "Запись удалена: " + e;
    }
}
