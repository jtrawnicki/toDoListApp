package pl.jtrawnicki.todolistapp.tasks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {


    List<Task> findAllByCategoryId(UUID id);
}
