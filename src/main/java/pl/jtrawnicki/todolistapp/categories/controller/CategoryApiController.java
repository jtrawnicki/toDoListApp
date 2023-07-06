package pl.jtrawnicki.todolistapp.categories.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;
import pl.jtrawnicki.todolistapp.categories.service.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("{id}")
    public Category getCategory(@PathVariable UUID id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Category udpateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }
}
