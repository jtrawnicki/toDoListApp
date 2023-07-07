package pl.jtrawnicki.todolistapp.tasks.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(readOnly = true)
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task getTask(UUID id) {

        return taskRepository.getReferenceById(id);
    }

    @Transactional
    public Task createTask(Task taskRequest) {

        Task task = new Task();

        task.setName(taskRequest.getName());

        return taskRepository.save(task);



    }

    @Transactional
    public Task updateTask(UUID id, Task taskRequest) {
        Task task = taskRepository.getReferenceById(id);

        task.setName(taskRequest.getName());

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }



    @Transactional(readOnly = true)
    public List<Task> findAllByCategoryId(UUID id) {
        return taskRepository.findAllByCategoryId(id);
    }
}
