package pl.jtrawnicki.todolistapp.categories.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;
import pl.jtrawnicki.todolistapp.categories.service.CategoryService;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;

import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

    private final CategoryService categoryService;

    private final TaskService taskService;

    public CategoryViewController(CategoryService categoryService, TaskService taskService) {
        this.categoryService = categoryService;
        this.taskService = taskService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("categories", categoryService.getCategories());

        return "category/index";
    }


    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("category", categoryService.getCategory(id));
        model.addAttribute("tasks", taskService.getTasks());

        return "category/single";
    }

    @GetMapping("{id}/edit")
    public String editView(Model model, @PathVariable UUID id) {
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("category", categoryService.getCategory(id));
        model.addAttribute("tasks", taskService.getTasks());

        return "category/edit";
    }

    @PostMapping("{id}/edit")
    public String edit(@ModelAttribute("category") Category category, @PathVariable UUID id) {
        categoryService.updateCategory(id, category);

        return "redirect:/categories";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("category", new Category());

        return "category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("category") Category category) {
        categoryService.createCategory(category);

        return "redirect:/categories";
    }

    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));

        return "category/delete";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable UUID id) {
        categoryService.deleteCategory(id);

        return "redirect:/categories";
    }


}
