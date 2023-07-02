package pl.jtrawnicki.todolistapp.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.jtrawnicki.todolistapp.categories.model.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryDao {

    private ObjectMapper objectMapper;

    public CategoryDao() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Category> findAll() {

        return getCategories();
    }


    public List<Category> getCategories() {

        try {
            return objectMapper.readValue(Files.readString(Paths.get("./categories.txt")), new TypeReference<>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    public void add(Category category) {

        List<Category> categories = getCategories();
        categories.add(category);

        saveCategories(categories);


    }

    private void saveCategories(List<Category> categories) {

        try {
            Files.writeString(Paths.get("./categories.txt"), objectMapper.writeValueAsString(categories));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Category update(UUID id, Category newCategory) {

        List<Category> categories = getCategories();

        Category currentCategory = new Category();

        for (Category category : categories) {
            if (category.getId().equals(id)) {
                currentCategory = category;
            }
        }

        currentCategory.setName(newCategory.getName());


        saveCategories(categories);

        return currentCategory;
    }


    public void delete(UUID id) {

        List<Category> categories = getCategories();

        categories.removeIf(category -> category.getId().equals(id));

        saveCategories(categories);

    }

    public Category findOne(UUID id) {

        List<Category> categories = getCategories();

        Category currentCategory = new Category();

        for (Category category : categories) {
            if (category.getId().equals(id)) {
                currentCategory = category;
            }
        }

        return currentCategory;
    }
}
