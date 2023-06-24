package pl.jtrawnicki.todolistapp.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jtrawnicki.todolistapp.tasks.model.Task;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;

import java.util.UUID;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("tasks", taskService.getTasks());

        return "task/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        model.addAttribute("tasks", taskService.getTasks());
        model.addAttribute("task", taskService.getTask(id));

        return "task/single";
    }

    @GetMapping("{id}/edit")
    public String editView(Model model, @PathVariable UUID id) {
        model.addAttribute("task", taskService.getTask(id));

        return "task/edit";
    }

    @PostMapping("{id}/edit")
    public String edit(@ModelAttribute("task") Task task, @PathVariable UUID id) {
        taskService.updateTask(id, task);

        return "redirect:/tasks";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("task", taskService.getTask(UUID.randomUUID()));

        return "task/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("task") Task task) {
        taskService.createTask(task);

        return "redirect:/tasks";
    }
}
