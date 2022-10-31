package com.example.driver;


import com.example.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll() throws Exception {

        this.mockMvc.perform(get("/emp/show"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void add() throws Exception {

        Employee emp = new Employee(10000, "Sarah", "Front End", 7698, new Date(2022-07-11), 76000, 3000, 20);

        this.mockMvc.perform(post("/emp/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(emp)))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    @Test
    public void getById() throws Exception {

        this.mockMvc.perform(get("/emp/getById/{id}", 10001))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void update() throws Exception {
        Employee emp = new Employee(10001, "Norah", "Front End", 7698, new Date(2022-07-11), 76000, 3000, 20);
        this.mockMvc.perform(put("/emp/update/{id}", 10001)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(emp)))
                .andDo(print())
                .andExpect(status().isAccepted());

    }

    @Test
    public void deleteById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/emp/delete/{id}", 10001))
                .andExpect(status().isAccepted());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}





















