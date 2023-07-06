package pl.jtrawnicki.todolistapp.tasks.service;

import org.springframework.stereotype.Service;
import pl.jtrawnicki.todolistapp.categories.domain.repository.CategoryRepository;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;
import pl.jtrawnicki.todolistapp.tasks.domain.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(UUID id) {

        return taskRepository.getReferenceById(id);
    }

    public Task createTask(Task taskRequest) {

        Task task = new Task();

        task.setName(taskRequest.getName());

        return taskRepository.save(task);


    }

    public Task updateTask(UUID id, Task taskRequest) {
        Task task = taskRepository.getReferenceById(id);

        task.setName(taskRequest.getName());

        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
