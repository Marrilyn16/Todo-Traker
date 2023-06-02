package com.example.week8task.Controller;

import com.example.week8task.dto.LoginDTO;
import com.example.week8task.dto.UserDTO;
import com.example.week8task.entity.User;
import com.example.week8task.implementation.TaskServiceImpl;
import com.example.week8task.implementation.UserServiceImpl;
import com.example.week8task.service.TaskService;
import com.example.week8task.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("userDTO", new UserDTO());
        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView getSignupForm(HttpSession session,ModelAndView mav) {
        if(session.getAttribute("id")!=null){
            mav.setViewName("redirect:/tasks");
        }else {
            mav.setViewName("index");
            mav.addObject("userDTO", new UserDTO());
        }
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("userDTO") UserDTO userDTO,ModelAndView model) {
        boolean user = userService.registerUser(userDTO);
        if (user) {
             model.addObject("redirect:/login");
        }else{
            model.addObject("redirect:/error");
        }
         return model;
    }




    @GetMapping("/login")
    public ModelAndView getLoginForm(HttpSession session,ModelAndView mav) {
        if(session.getAttribute("id")!=null){
            mav.setViewName("redirect:/tasks");
        }else{
        mav.setViewName("login");
        mav.addObject("userDTO", new UserDTO());
        }
        return mav;
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("userDTO") LoginDTO loginDTO, HttpSession session) {
        ModelAndView model= new ModelAndView();
        boolean user = userService.loginUser(loginDTO);
       if(!user){

          model.setViewName("redirect:/login");
       }else {
           System.out.println(session.getAttribute("id"));
           model.setViewName("redirect:/tasks");
       }

       return model;
    }






    @GetMapping("/users/{id}")
    public ModelAndView getUserDetail(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("user-detail");
        mav.addObject("user", userDTO);
        return mav;
    }
    @GetMapping("/users/edit/{id}")
    public ModelAndView getEditUserForm(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("edit-user");
        mav.addObject("userDTO", user);
        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";

    }

    @GetMapping("/error")
    public String error(Model model) {

        return "error";

    }

}

