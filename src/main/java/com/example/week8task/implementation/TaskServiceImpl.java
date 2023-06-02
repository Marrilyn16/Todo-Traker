package com.example.week8task.implementation;

import com.example.week8task.dto.TaskDTO;
import com.example.week8task.entity.User;
import com.example.week8task.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.week8task.repository.TaskRepository;
import com.example.week8task.service.TaskService;
import com.example.week8task.entity.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }
    public List<Task> getTaskByUserid(Long userid) {
        return taskRepository.findTasksUserid(userid);
    }
    public int getUserTask(Long userid,String status) {
        return taskRepository.findTasksUseridByPending(userid,status).size();
    }


    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findTaskById(id).orElse(null);
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findTasksByStatus(status);
    }

    @Override
    public List<Task> getTasksBySearch(String query) {
        return null;
    }

    @Override
    public Task saveTask(TaskDTO taskDTO,Long id) {
        Task task = new Task(taskDTO);


        task.setUser_id(id);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
    @Override
    public Task updateTask(Long id, TaskDTO taskDTO)  {
        Task task = new Task(taskDTO);
        task.setUpdatedAt(LocalDateTime.now());
        Task existingTask = taskRepository.findTaskById(id).orElse(null);
        if (existingTask!=null) {
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
           return taskRepository.save(existingTask);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTask(Long id) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }

}
