package pl.jtrawnicki.todolistapp.tasks.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.NumberFormat;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;

import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private UUID id;

    @NotBlank(message = "{toDoList.validation.name.NotBlank.message}")
    @Size(min = 3, max = 255, message = "{toDoList.validation.size.NotBlank.message}")
    private String name;

    @Min(value = 1, message = "Priority must be between 1 and 9")
    @Max(value = 9, message = "Priority must be between 1 and 9")
    private int priority;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", category=" + category +
                '}';
    }
}


