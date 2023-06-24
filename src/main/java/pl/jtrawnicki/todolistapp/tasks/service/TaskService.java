package pl.jtrawnicki.todolistapp.tasks.service;

import org.springframework.stereotype.Service;
import pl.jtrawnicki.todolistapp.tasks.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    List<Task> tasks = new ArrayList<>();



    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(UUID id) {
        return new Task("Task");
    }

    public Task createTask(Task task) {
        task.setId(UUID.randomUUID());

        tasks.add(task);
        
        return task;
    }

    public Task updateTask(UUID id, Task task) {
        return task;
    }

    public void deleteTask(UUID id) {
    }
}
