package pl.jtrawnicki.todolistapp.categories.service;

import org.springframework.stereotype.Service;
import pl.jtrawnicki.todolistapp.categories.model.Category;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    List<Category> categories = Arrays.asList(new Category("Category 1"), new Category("Category 2"));

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategory(UUID id) {
        return new Category("Category123");
    }

    public Category createCategory(Category category) {
        category.setId(UUID.randomUUID());

        return category;
    }

    public Category updateCategory(UUID id, Category category) {
        return category;
    }

    public void deleteCategory(UUID id) {
    }
}
