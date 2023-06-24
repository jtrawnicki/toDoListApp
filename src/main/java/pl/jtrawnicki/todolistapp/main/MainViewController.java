package pl.jtrawnicki.todolistapp.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;

@Controller
@RequestMapping("/main")
public class MainViewController {

    private final TaskService taskService;

    public MainViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("tasks", taskService.getTasks());

        return "main/index";
    }
}
