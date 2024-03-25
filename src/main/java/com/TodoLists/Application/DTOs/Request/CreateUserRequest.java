package com.TodoLists.Application.DTOs.Request;

import lombok.Data;


@Data
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
}
