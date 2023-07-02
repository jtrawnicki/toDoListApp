package pl.jtrawnicki.todolistapp.tasks.service;

import org.springframework.stereotype.Service;
import pl.jtrawnicki.todolistapp.dao.TaskDao;
import pl.jtrawnicki.todolistapp.tasks.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskDao taskDao;

    public TaskService() {
        this.taskDao = new TaskDao();
    }

    public List<Task> getTasks() {
        return taskDao.findAll();
    }

    public Task getTask(UUID id) {

        List<Task> tasks = taskDao.getTasks();

        Task currentTask = new Task();

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                currentTask = task;
            }
        }

        return currentTask;
    }

    public Task createTask(Task task) {
        task.setId(UUID.randomUUID());

        taskDao.add(task);
        
        return task;
    }

    public Task updateTask(UUID id, Task newTask) {


        Task updatedTask = taskDao.update(id, newTask);

        return updatedTask;
    }

    public void deleteTask(UUID id) {

        taskDao.deleteTask(id);
    }
}
