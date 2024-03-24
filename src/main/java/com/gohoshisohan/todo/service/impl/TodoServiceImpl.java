package com.gohoshisohan.todo.service.impl;

import com.gohoshisohan.todo.entity.Task;
import com.gohoshisohan.todo.repository.TaskRepository;
import com.gohoshisohan.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    public TodoServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = repository.findAll();
        return tasks;
    }

    @Override
    public Task get(String id) {
        Task task = repository.findById(id).orElse(null);
        return task;
    }

    TaskRepository repository;

    @Override
    public void createTask(Task task) {
        repository.save(task);
    }
}
