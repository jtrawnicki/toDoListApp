package pl.jtrawnicki.todolistapp.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jtrawnicki.todolistapp.tasks.model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskDao {

    private ObjectMapper objectMapper;

    public TaskDao() {
        this.objectMapper = new ObjectMapper();
    }


    public List<Task> findAll() {
        return getTasks();

    }

    public List<Task> getTasks() {

        try {
            return objectMapper.readValue(Files.readString(Paths.get("./tasks.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void add(Task task) {

        List<Task> tasks = getTasks();
        tasks.add(task);

        saveTasks(tasks);

    }

    private void saveTasks(List<Task> tasks) {

        try {
            Files.writeString(Paths.get("./tasks.txt"), objectMapper.writeValueAsString(tasks));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Task update(UUID id, Task newTask) {

        List<Task> tasks = getTasks();

        Task currentTask = new Task();

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                currentTask = task;
            }
        }

        currentTask.setName(newTask.getName());

        saveTasks(tasks);

        return currentTask;
    }

    public Task findOne(UUID id) {

        List<Task> tasks = getTasks();

        Task currentTask = new Task();

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                currentTask = task;
            }
        }

        return currentTask;
    }

    public void deleteTask(UUID id) {
        List<Task> tasks = getTasks();

        tasks.removeIf(task -> task.getId().equals(id));

        saveTasks(tasks);
    }
}
