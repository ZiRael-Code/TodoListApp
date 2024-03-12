package com.TodoLists.Application.DTOs;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;

}
