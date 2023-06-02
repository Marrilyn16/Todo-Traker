package com.example.week8task.dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;

    private String userName;

    private String email;
    private String firstName;
    private String lastName;

    private String contactNo;

    private String password;
}
