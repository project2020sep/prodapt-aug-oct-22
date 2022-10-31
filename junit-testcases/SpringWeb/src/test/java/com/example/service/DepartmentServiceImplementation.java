package com.example.service;

import com.example.driver.repositories.DepartmentRepository;
import com.example.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService {
    @Autowired
    DepartmentRepository dRepo;
    @Override
    public List<Department> getAll() {

        //Gather all the entries for department from the database and return as a list
        try{
            List<Department> dList = dRepo.findAll();
            if(!dList.isEmpty())
                return dList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> insert(Department d) {
        //Search if the primary key/dept already exists in the database
        //insert the new entry if the primary key is not taken
        try{
            Optional<Department> dept = dRepo.findById(d.getDeptno());
            if(dept.isEmpty()) {
                dRepo.save(d);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

    @Override
    public ResponseEntity<String> update(Department d, int id) {

        //Find the entry in the database using deptno
        //update the information
        try{
            Optional<Department> dept = dRepo.findById(id);
            dept.get().setDeptno(d.getDeptno());
            dept.get().setDname(d.getDname());
            dept.get().setLoc(d.getLoc());
            dRepo.save(dept.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception exc )
        {
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> delete(int deptno) {
        //Search if the entry existed in the database
        //If so, delete it
        try{
            Optional<Department> dept = dRepo.findById(deptno);
            if(!dept.isEmpty()) {
                dRepo.deleteById(deptno);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }

        }catch(Exception exc){
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Optional<Department> getDepartmentById(int deptno) {

        //Search the entry by id and return it
        //return optional.empty if not exist
        try{
            Optional<Department> dept =  dRepo.findById(deptno);
            if(!dept.isEmpty())
            {
                return dept;
            }
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity<String> deleteAll() {

        //Check if the database is empty
        //if now, delete all the entries in the table
        try{
            List<Department>dept = dRepo.findAll();
            if(dept!= null) {
                dRepo.deleteAll();
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
