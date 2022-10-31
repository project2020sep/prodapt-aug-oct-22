package com.example.driver;

import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.*")
@EnableJpaRepositories(basePackages = "com.*")
@EntityScan(basePackages = "com.*")
public class SpringDriver {
    @Autowired
    EmployeeService eService;

    //@Autowired
    //DepartmentService dService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDriver.class, args);

    }
}
