package pl.jtrawnicki.todolistapp.categories.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;
import pl.jtrawnicki.todolistapp.categories.service.CategoryService;
import pl.jtrawnicki.todolistapp.common.dto.Message;
import pl.jtrawnicki.todolistapp.tasks.service.TaskService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String indexView(@PageableDefault(value = 5) Pageable pageable, Model model) {
        Page<Category> categoriesPage = categoryService.getCategories(pageable);
        model.addAttribute("categoriesPage", categoriesPage);
        paging(model, categoriesPage);

        return "category/index";
    }


    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        model.addAttribute("category", categoryService.getCategory(id));
        model.addAttribute("tasks", taskService.findAllByCategoryId(id));

        return "category/single";
    }

    @GetMapping("{id}/edit")
    public String editView(Model model, @PathVariable UUID id) {
        model.addAttribute("category", categoryService.getCategory(id));
        model.addAttribute("tasks", taskService.getTasks(Pageable.unpaged()));

        return "category/edit";
    }

    @PostMapping("{id}/edit")
    public String edit(@PathVariable UUID id,
                       @Valid @ModelAttribute("category") Category category,
                       BindingResult bindingResult,
                       RedirectAttributes ra,
                       Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return "category/edit";
        }

        try {
            categoryService.updateCategory(id, category);
            ra.addFlashAttribute(Message.info("Kategoria edytowana"));
        } catch (Exception e) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Nieznany błąd zapisu"));
            return "category/edit";
        }

        return "redirect:/categories";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("category", new Category());

        return "category/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") Category category,
                      BindingResult bindingResult,
                      RedirectAttributes ra,
                      Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return "category/add";
        }

        try{
            categoryService.createCategory(category);
            ra.addFlashAttribute(Message.info("Kategoria utworzona"));
        } catch (Exception e) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Nieznant błąd zapisu"));
            return "category/add";
        }

        return "redirect:/categories";
    }

    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));

        return "category/delete";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        try {
            categoryService.deleteCategory(id);
            ra.addFlashAttribute(Message.info("Kategoria usunięta"));
        } catch (Exception e) {
            ra.addFlashAttribute(Message.error("Nieznany błąd. Nie udało się usunąć kategorii"));
        }
        return "redirect:/categories";
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
