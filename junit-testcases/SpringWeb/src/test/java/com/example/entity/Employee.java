package com.example.entity;

import javax.persistence.*;
import java.sql.Date;


@Table(name = "emp")
@Entity
public class Employee {

    @Id
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private int sal;
    private int comm;
    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "dept",
        joinColumns =
                {@JoinColumn(name = "deptno", referencedColumnName = "deptno")},
        inverseJoinColumns =
                {@JoinColumn(name = "deptno", referencedColumnName = "deptno")})*/
    private int deptno;

    public Employee()
    {
        super();
    }
    public Employee(int empno, String ename, String job, int mgr, Date hiredDate, int sal, int comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredDate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHireDate() {
        return hiredate;
    }

    public void setHireDate(Date hireDate) {
        this.hiredate = hireDate;
    }


    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getComm() {
        return comm;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

}
