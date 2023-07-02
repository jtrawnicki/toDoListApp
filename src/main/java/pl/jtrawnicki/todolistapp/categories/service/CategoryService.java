package pl.jtrawnicki.todolistapp.categories.service;

import org.springframework.stereotype.Service;
import pl.jtrawnicki.todolistapp.categories.model.Category;
import pl.jtrawnicki.todolistapp.dao.CategoryDao;

import java.util.*;

@Service
public class CategoryService {

    private CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryDao.findAll();

        return categories;
    }

    public Category getCategory(UUID id) {

        List<Category> categories = getCategories();

        Category currentCategory = new Category();

        for (Category category : categories) {
            if (category.getId().equals(id))
                currentCategory = category;
        }

        return currentCategory;


    }

    public Category createCategory(Category category) {
        category.setId(UUID.randomUUID());

        categoryDao.add(category);


        return category;
    }

    public Category updateCategory(UUID id, Category newCategory) {


        Category updatedCategory = categoryDao.update(id, newCategory);

        return updatedCategory;

    }

    public void deleteCategory(UUID id) {

        categoryDao.delete(id);
    }
}
