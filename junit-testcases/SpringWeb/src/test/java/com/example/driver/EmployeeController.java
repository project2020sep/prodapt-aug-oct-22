package com.example.driver;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService service;


    @GetMapping("/show")
    @ResponseBody
    public List<Employee> getEmployees()
    {
        return service.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> insert(@RequestBody Employee emp)
    {
        //Check if there is information pass in from the post request
        //if yes, call insert service to do the insertion
        try{
            if(emp != null) {
                return service.insert(emp);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> update(@RequestBody Employee emp, @PathVariable int id)
    {
        //Check if there is information pass in from the post request
        //if yes, call update service to update the information
        try{
            if(emp != null)
                return service.update(emp, id);
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Optional<Employee> getById(@PathVariable int id)
    {
        return service.getEmployeeById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable int id)
    {
        return service.delete(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseBody
    public ResponseEntity<String> deleteAll()
    {
        return service.deleteAll();
    }

}

