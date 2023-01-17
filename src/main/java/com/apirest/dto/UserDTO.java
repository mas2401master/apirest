package com.apirest.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    private long id;
    @NotEmpty(message = "The name must not be empty or null")
    private String name;
    @NotEmpty(message = "The username must not be empty or null")
    private String username;
    @NotEmpty(message = "The email must not be empty or null")
    @Email
    private String email;
    @NotEmpty(message = "The password must not be empty or null")
    @Size(min = 5,message = "minimum 5 characters in password")
    private String password;
}
