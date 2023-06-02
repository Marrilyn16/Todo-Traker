package com.example.week8task.entity;

import com.example.week8task.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator(name="users_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;

    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private String contactNo;



}





