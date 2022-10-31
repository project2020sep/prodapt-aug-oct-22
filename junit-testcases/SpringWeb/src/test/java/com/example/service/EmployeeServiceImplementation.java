package com.example.service;

import com.example.driver.repositories.EmployeeRepository;
import com.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation  implements EmployeeService{
    @Autowired
    EmployeeRepository repo;
    @Override
    public List<Employee> getAll() {
        //Gather all the entry from the database and return as a list
        try{
            List<Employee> eList = repo.findAll();
            if(!eList.isEmpty())
                return eList;
        }catch(Exception exc){
            System.out.println(exc);
        }
        return null;

    }

    @Override
    public ResponseEntity<String> insert(Employee e) {
        //Search if the primary key/empno already exists in the database
        //insert the new entry if the primary key is not taken
        try{
            Optional<Employee> emp = repo.findById(e.getEmpno());
            if(emp.isEmpty()) {
                repo.save(e);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    }

    @Override
    public ResponseEntity<String> update(Employee e, int id) {

        //Find the entry in the database using empno
        //update the information
        try{
            Optional<Employee> emp = repo.findById(id);
            emp.get().setComm(e.getComm());
            emp.get().setEmpno(e.getEmpno());
            emp.get().setEname(e.getEname());
            emp.get().setDeptno(e.getDeptno());
            emp.get().setSal(e.getSal());
            emp.get().setMgr(e.getMgr());
            emp.get().setJob(e.getJob());
            emp.get().setHireDate(e.getHireDate());
            repo.save(emp.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception exc )
        {
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> delete(int empno) {
        //Search if the entry existed in the database
        //If so, delete it
        try{
            Optional<Employee> emp = repo.findById(empno);
            repo.deleteById(empno);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception exc){
            System.out.println(exc);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Optional<Employee> getEmployeeById(int empno) {

        //Search the entry by id and return it
        //return optional.empty if not exist
        try{
            Optional<Employee> emp =  repo.findById(empno);
            if(!emp.isEmpty())
            {
                return emp;
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
            List<Employee>emp = repo.findAll();
            if(emp!= null) {
                repo.deleteAll();
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}