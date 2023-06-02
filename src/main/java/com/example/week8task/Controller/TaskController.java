package com.example.week8task.Controller;

import com.example.week8task.dto.TaskDTO;
import com.example.week8task.entity.User;
import com.example.week8task.exception.ResourceNotFoundException;
import com.example.week8task.implementation.TaskServiceImpl;
import com.example.week8task.implementation.UserServiceImpl;
import com.example.week8task.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.week8task.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    private final TaskServiceImpl taskService;
    private final UserServiceImpl userService;
    @Autowired
    public TaskController(UserServiceImpl userService,TaskServiceImpl taskService) {
        this.taskService = taskService;
        this.userService=userService;
    }
    @GetMapping("/tasks")
    public ModelAndView showDashboard(HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        List<Task> tasks = taskService.getTaskByUserid(id);
        ModelAndView mav = new ModelAndView("activity_dashBoard");

        mav.addObject("countTaskByPending", taskService.getUserTask(id,"not started"));
        mav.addObject("countTaskByDone", taskService.getUserTask(id,"completed"));
        mav.addObject("countTask", taskService.getUserTask(id,"in progress"));
        mav.addObject("tasks", tasks);
        List<User> users = userService.getAllUsers();
        mav.addObject("users", users);
        return mav;
    }
    @GetMapping("/tasks/add")
    public ModelAndView getAddTaskForm() {
        ModelAndView mav = new ModelAndView("add_Task");
        mav.addObject("taskDTO", new TaskDTO());
        return mav;
    }

    @PostMapping("/tasks/save")
    public ModelAndView saveTask(@ModelAttribute("taskDTO") TaskDTO taskDTO,HttpSession session) {
        Long id = (Long)session.getAttribute("id");
        Task savedTask = taskService.saveTask(taskDTO,id);
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks"); // Redirect to the pending tasks page
        return modelAndView;
    }

    @GetMapping("/tasks/task-detail/{id}")
    public ModelAndView showTaskDetail(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        ModelAndView mav = new ModelAndView("task-detail");
        mav.addObject("task", task);
        return mav;
    }

    @GetMapping("/tasks/{status}")
    public ModelAndView getTasksByStatus(@PathVariable String status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        ModelAndView modelAndView = new ModelAndView("tasks");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

    @PostMapping("/tasks/update")
    public ModelAndView updateTask(@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("statuscode") String statuscode,HttpSession session,ModelAndView modelAndView )  {
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setTitle(title);
    taskDTO.setDescription(description);
    taskDTO.setStatus(statuscode);
        Long id =(Long)session.getAttribute("id");
        Task updatedTask = taskService.updateTask(id, taskDTO);
        //System.out.println("this is "+id+ "the dto "+taskDTO);
        if(updatedTask!=null){
            modelAndView.setViewName("redirect:/tasks");
        }else{
            modelAndView.setViewName("redirect:/tasks/edit/"+id);

        } // Redirect to the pending tasks page
        return modelAndView;
    }

    @GetMapping("/tasks/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Long id) throws ResourceNotFoundException {
        taskService.deleteTask(id);
        return new ModelAndView("redirect:/tasks");
    }
    @GetMapping("/tasks/edit/{id}")
    public ModelAndView getEditTaskForm(@PathVariable Long id) throws ResourceNotFoundException {
        Task task = taskService.getTaskById(id);
        System.out.println(task);
        ModelAndView mav = new ModelAndView("edit_Task");
        mav.addObject("taskDTO", task);
        return mav;
    }

}



