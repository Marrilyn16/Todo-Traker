package com.example.week8task.implementation;

import com.example.week8task.dto.LoginDTO;
import com.example.week8task.dto.UserDTO;
import com.example.week8task.entity.User;
import com.example.week8task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)

class UserServiceImplTest {


    private UserServiceImpl userService;
    @InjectMocks
    private UserRepository userRepository;

    @Test
    void registerUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("sgjhsk");
        userDTO.setEmail("fsgjsf");
        userDTO.setPassword("djgd");
        userDTO.setFirstName("dfgdfs");
        User user = User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .contactNo(userDTO.getContactNo())
                .build();
        assertNotNull(user);


    }

    @Test
    void loginUser() {
        LoginDTO user = new LoginDTO();
        user.setEmail("marrilyno16@gmail.com");
        user.setPassword("1234");
        assertNotNull(userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword()));

    }



}