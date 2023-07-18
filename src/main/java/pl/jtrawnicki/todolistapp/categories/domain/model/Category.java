package pl.jtrawnicki.todolistapp.categories.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "categories")
public class Category {

    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;


    public Category() {
        this.id = UUID.randomUUID();
    }

    public Category(String name) {
        this();
        this.name = name;

    }

    public Category addTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        tasks.add(task);
        task.setCategory(this);

        return this;
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
