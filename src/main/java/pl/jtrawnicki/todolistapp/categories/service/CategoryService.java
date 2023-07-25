package pl.jtrawnicki.todolistapp.categories.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;
import pl.jtrawnicki.todolistapp.categories.domain.repository.CategoryRepository;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;


import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategory(UUID id) {

        return categoryRepository.getReferenceById(id);


    }

    @Transactional
    public Category createCategory(Category categoryRequest) {

        Category category = new Category();

        category.setName(categoryRequest.getName());

        return categoryRepository.save(category);

    }

    @Transactional
    public Category updateCategory(UUID id, Category categoryRequest) {


        Category category = categoryRepository.getReferenceById(id);

        category.setName(categoryRequest.getName());

        return categoryRepository.save(category);

    }

    @Transactional
    public void deleteCategory(UUID id) {

        categoryRepository.deleteById(id);
    }


    @Transactional
    public int getNumberOfCategories() {
        return categoryRepository.getNumberOfCategories();
    }

}
