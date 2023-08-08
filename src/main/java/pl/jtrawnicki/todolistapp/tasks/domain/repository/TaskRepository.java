package pl.jtrawnicki.todolistapp.tasks.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jtrawnicki.todolistapp.tasks.domain.model.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {


    List<Task> findAllByCategoryId(UUID id);


    @Query(value = "select COUNT(t) from Task t")
    int getNumberOfTasks();

    @Query(value = "select t from Task t ORDER BY t.priority DESC")
    List<Task> getTasksSortedByPriority();

    Page<Task> findAll(Pageable pageable);
}
