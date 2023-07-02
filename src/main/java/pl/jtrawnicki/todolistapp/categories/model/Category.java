package pl.jtrawnicki.todolistapp.categories.model;

import pl.jtrawnicki.todolistapp.tasks.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Category {

    private String name;

    private UUID id;

    private List<Task> tasks;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.tasks = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
