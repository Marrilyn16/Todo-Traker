package com.example.week8task.implementation;

import com.example.week8task.dto.LoginDTO;
import com.example.week8task.dto.UserDTO;
import com.example.week8task.entity.User;
import com.example.week8task.repository.UserRepository;
import com.example.week8task.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HttpServletRequest req;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,HttpServletRequest req) {
        this.userRepository = userRepository;
        this.req= req;
    }

    @Override
    public boolean registerUser(UserDTO userDTO) {
        boolean status=false;
        User user = User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .contactNo(userDTO.getContactNo())
                .build();
        if (user!=null){
            status=true;
            userRepository.save(user);
        }
//        user.setCreatedAt(LocalDateTime.now())
        return status;
    }



    @Override
    public boolean loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if(user == null) {
            return false;
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("id",user.getUserId());
            System.out.println(session.getAttribute("id"));
            return true;
        }

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User getUserByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    @Override
    public UserDTO getUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.map(this::mappedToDTO).orElse(null);
    }

    private UserDTO mappedToDTO(User user) {
        UserDTO userDTO = UserDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
//        userDTO.setCreatedAt(user.getCreatedAt());
//        userDTO.setUpdatedAt(user.getUpdatedAt());setUpdatedAt
        userDTO.setUserId(user.getUserId());

        return userDTO;
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
