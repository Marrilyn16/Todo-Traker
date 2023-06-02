package com.example.week8task.service;

import com.example.week8task.dto.TaskDTO;
import com.example.week8task.exception.ResourceNotFoundException;
import com.example.week8task.entity.Task;
import java.util.List;

public interface TaskService {
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksBySearch(String query);
    Task saveTask(TaskDTO taskDTO,Long id);

    Task updateTask(Long id, TaskDTO taskDTO) throws ResourceNotFoundException;
    void deleteTask(Long id) throws ResourceNotFoundException;
}
