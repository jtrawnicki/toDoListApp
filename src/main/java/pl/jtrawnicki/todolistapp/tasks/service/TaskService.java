package pl.jtrawnicki.todolistapp.tasks.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;
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
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksSortedByPriority() {
        return taskRepository.getTasksSortedByPriority();
    }

    @Transactional(readOnly = true)
    public Task getTask(UUID id) {

        return taskRepository.getReferenceById(id);
    }

    @Transactional
    public Task createTask(Task taskRequest) {

        Task task = new Task();

        task.setName(taskRequest.getName());
        task.setPriority(taskRequest.getPriority());

        UUID categoryId = taskRequest.getCategory().getId();

        Category category = categoryRepository.getReferenceById(categoryId);


        categoryRepository.save(category);
        category.addTask(task);
        taskRepository.save(task);

        return task;
    }

    @Transactional
    public Task updateTask(UUID id, Task taskRequest) {
        Task task = taskRepository.getReferenceById(id);

        task.setName(taskRequest.getName());
        task.setPriority(taskRequest.getPriority());

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

    @Transactional(readOnly = true)
    public int getNumberOfTasks() {
        return taskRepository.getNumberOfTasks();
    }


}
