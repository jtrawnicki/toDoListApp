package pl.jtrawnicki.todolistapp.tasks.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;

import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    private String name;

    @Id
    private UUID id;

    @ManyToOne
    private Category category;

    public Task() {
        this.id = UUID.randomUUID();
    }

    public Task(String name) {
        this();
        this.name = name;

    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
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
        return "Task{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}


