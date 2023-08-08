package pl.jtrawnicki.todolistapp.tasks.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jtrawnicki.todolistapp.categories.service.CategoryService;
import pl.jtrawnicki.todolistapp.common.dto.Message;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;

import java.awt.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String indexView(
            @RequestParam(value = "s", required = false) String search,
            @PageableDefault(value = 5, sort = "priority", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        Page<Task> tasksPage = taskService.getTasks(search, pageable);
        model.addAttribute("tasksPage", tasksPage);
        model.addAttribute("search", search);
        paging(model, tasksPage);

        return "task/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        model.addAttribute("tasks", taskService.getTasks(Pageable.unpaged()));
        model.addAttribute("task", taskService.getTask(id));

        return "task/single";
    }

    @GetMapping("{id}/edit")
    public String editView(Model model, @PathVariable UUID id) {
        model.addAttribute("task", taskService.getTask(id));

        return "task/edit";
    }

    @PostMapping("{id}/edit")
    public String edit(@PathVariable UUID id,
                       @Valid @ModelAttribute("task") Task task,
                       BindingResult bindingResult,
                       RedirectAttributes ra,
                       Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("task", task);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return "task/edit";
        }

        try {
            taskService.updateTask(id, task);
            ra.addFlashAttribute(Message.info("Zadanie pomyślnie edytowane"));

        } catch (Exception e) {
            model.addAttribute("task", task);
            model.addAttribute("message", Message.error("Nieznant błąd zapisu"));
            return "task/edit";
        }

        return "redirect:/tasks";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("categories", categoryService.getCategories(Pageable.unpaged()));

        return "task/add";
    }

    @PostMapping
    public String add(@Valid @ModelAttribute("task") Task task,
                      BindingResult bindingResult,
                      RedirectAttributes ra,
                      Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("task", task);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            model.addAttribute("categories", categoryService.getCategories(Pageable.unpaged()));
            return "task/add";
        }

        try{
            taskService.createTask(task);
            ra.addFlashAttribute(Message.info("Zadanie dodane pomyślnie"));

        } catch (Exception e) {
            model.addAttribute("task", task);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return "task/add";
        }

        return "redirect:/tasks";
    }

    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id, Model model) {
        model.addAttribute("task", taskService.getTask(id));

        return "task/delete";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        taskService.deleteTask(id);
        ra.addFlashAttribute("message", Message.info("Zadanie usunięte pomyślnie"));

        return "redirect:/tasks";
    }

    private void paging(Model model, Page page) {
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

}
