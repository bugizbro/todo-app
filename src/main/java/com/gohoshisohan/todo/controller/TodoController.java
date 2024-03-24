package com.gohoshisohan.todo.controller;

import com.gohoshisohan.todo.entity.Task;
import com.gohoshisohan.todo.model.TaskCreateDTO;
import com.gohoshisohan.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TodoController {

	private final TodoService service;

	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}

	@GetMapping()
	public ResponseEntity<List<Task>> getMethodName() {
		List<Task> list = service.getAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable("id") String id) {
		Task task = service.get(id);
		if (task == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(task);
	}

	@PostMapping
	public ResponseEntity<Task> createTask(@Validated @RequestBody TaskCreateDTO taskDto, BindingResult br) {
		if (br.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		Task task = new Task();
		task.setName(taskDto.getName());
		service.createTask(task);
		return ResponseEntity.ok().build();
	}
}
