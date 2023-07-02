package pl.jtrawnicki.todolistapp.tasks.model;

import pl.jtrawnicki.todolistapp.categories.model.Category;

import java.util.UUID;

public class Task {

    private String name;

    private Category category;

    private UUID id;

    public Task() {
    }

    public Task(String name, Category category) {
        this.name = name;
        this.category = category;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", id=" + id +
                '}';
    }
}


