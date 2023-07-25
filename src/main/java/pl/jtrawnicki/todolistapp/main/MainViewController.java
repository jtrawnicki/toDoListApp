package pl.jtrawnicki.todolistapp.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jtrawnicki.todolistapp.categories.service.CategoryService;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;

@Controller
@RequestMapping("/main")
public class MainViewController {

    private final TaskService taskService;

    private final CategoryService categoryService;

    public MainViewController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("tasks", taskService.getTasksSortedByPriority());
        model.addAttribute("numberOfTasks", taskService.getNumberOfTasks());
        model.addAttribute("numberOfCategories", categoryService.getNumberOfCategories());

        return "main/index";
    }
}
