package pl.jtrawnicki.todolistapp.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jtrawnicki.todolistapp.categories.service.CategoryService;
import pl.jtrawnicki.todolistapp.tasks.model.Task;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;

import java.util.UUID;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    private final CategoryService categoryService;

    public TaskViewController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
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
        model.addAttribute("categories", categoryService.getCategories());

        return "task/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("task") Task task) {
        taskService.createTask(task);

        return "redirect:/tasks";
    }

    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id, Model model) {
        model.addAttribute("task", taskService.getTask(id));

        return "task/delete";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable UUID id) {
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }

}
