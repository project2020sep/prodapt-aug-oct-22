package com.example.driver;

import com.example.entity.Department;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Using restController
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    DepartmentService dService;



    @GetMapping("/show")
    @ResponseBody
    public List<Department> getDepartments()
    {
        return dService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> insert(@RequestBody Department dept)
    {
        //check if receive anything from the Post request
        //insert to the database
        try{
            if(dept != null) {
                return dService.insert(dept);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> update(@RequestBody Department dept, @PathVariable int id)
    {
        //check if new information pass in from the put request
        //update the information with primary departmentId
        try{
            if(dept != null)
                return dService.update(dept, id);
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Optional<Department> getById(@PathVariable int id)
    {

        return dService.getDepartmentById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable int id)
    {
        return dService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseBody
    public ResponseEntity<String> deleteAll()
    {
        return dService.deleteAll();
    }

}

