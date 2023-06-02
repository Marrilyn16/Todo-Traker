package com.example.week8task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.week8task.entity.Task;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT * FROM tasks WHERE id = ?1", nativeQuery = true)
    Optional<Task> findTaskById(Long id);
    @Query(value = "SELECT * FROM tasks", nativeQuery = true)
    List<Task> findAllTasks();
    @Query(value = "SELECT * FROM tasks WHERE status = ?1", nativeQuery = true)
    List<Task> findTasksByStatus(String status);

    @Query(value = "SELECT * FROM tasks WHERE user_id = ?1", nativeQuery = true)
    List<Task> findTasksUserid(Long userid);
    @Query(value = "SELECT * FROM tasks WHERE user_id = ?1 AND status=?2", nativeQuery = true)

    List<Task> findTasksUseridByPending(Long userid,String status);
}
