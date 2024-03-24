package com.gohoshisohan.todo.service;

import com.gohoshisohan.todo.entity.Task;
import com.gohoshisohan.todo.repository.TaskRepository;
import com.gohoshisohan.todo.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TodoServiceImplTest {

    TodoService service;
    TaskRepository repository;

    @BeforeEach
    public void beforeEach() {
        this.repository = mock(TaskRepository.class);
        this.service = new TodoServiceImpl(this.repository);
    }

    @Test
    public void タスクを全て取得() {
        Task t1 = new Task() {{setName("洗濯");}};
        Task t2 = new Task() {{setName("掃除");}};
        Task t3 = new Task() {{setName("運動");}};
        when(this.repository.findAll()).thenReturn(List.of(t1, t2, t3));

        List<Task> result = this.service.getAll();

        assertEquals(3, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void タスクを1件取得() {
        Task task = new Task();
        when(this.repository.findById("1")).thenReturn(Optional.of(task));

        Task result = this.service.get("1");

        assertEquals(task, result);
        verify(repository, times(1)).findById("1");
    }

    @Test
    public void タスクを1件登録() {
        Task task = new Task();

        this.service.createTask(task);

        verify(repository, times(1)).save(task);
    }
}
