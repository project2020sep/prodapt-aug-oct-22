package com.example.driver;

import com.example.entity.Department;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll() throws Exception {

        this.mockMvc.perform(get("/dept/show"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void add() throws Exception {

        Department dept1 = new Department(50, "Computer Science", "California");

        this.mockMvc.perform(post("/dept/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dept1)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void getById() throws Exception {

        this.mockMvc.perform(get("/dept/getById/{id}", 50))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void update() throws Exception {
        Department dept = new Department(60, "Computer Science", "Pleasanton");
        this.mockMvc.perform(put("/dept/update/{id}", 60)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dept)))
                .andDo(print())
                .andExpect(status().isAccepted());

    }

    @Test
    public void deleteById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/dept/delete/{id}", 50))
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