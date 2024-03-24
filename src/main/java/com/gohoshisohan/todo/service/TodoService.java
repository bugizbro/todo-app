package com.gohoshisohan.todo.service;

import com.gohoshisohan.todo.entity.Task;

import java.util.List;

public interface TodoService {

    List<Task> getAll();

    Task get(String id);

    void createTask(Task task);
}
