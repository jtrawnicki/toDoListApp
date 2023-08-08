package pl.jtrawnicki.todolistapp.tasks.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskApiController {

    private final TaskService taskService;

    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public Page<Task> getTasks(Pageable pageable) {
        return taskService.getTasks(pageable);
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task updateTask(@PathVariable UUID id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}
