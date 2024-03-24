package com.gohoshisohan.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gohoshisohan.todo.entity.Task;
import com.gohoshisohan.todo.model.TaskCreateDTO;
import com.gohoshisohan.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @MockBean
    TodoService service;

    @Test
    public void タスク全件取得(@Autowired MockMvc mockMvc) throws Exception {
        given(this.service.getAll()).willReturn(List.of(new Task("1", "サッカー", LocalDate.now(), null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void タスクID指定取得(@Autowired MockMvc mockMvc) throws Exception {
        given(this.service.get("1")).willReturn(new Task("1", "サッカー", LocalDate.now(), null));

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void タスクID指定取得失敗(@Autowired MockMvc mockMvc) throws Exception {
        given(this.service.get("0")).willReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/0"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void タスク作成成功(@Autowired MockMvc mockMvc) throws Exception {
        TaskCreateDTO task = new TaskCreateDTO("掃除");

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void タスク作成失敗(@Autowired MockMvc mockMvc) throws Exception {
        TaskCreateDTO task = new TaskCreateDTO(null);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
