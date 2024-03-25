package com.TodoLists.Application.DTOs.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
private int id;
private String message;
private boolean loginStatus;
}
