package pl.jtrawnicki.todolistapp.categories.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jtrawnicki.todolistapp.categories.domain.model.Category;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
