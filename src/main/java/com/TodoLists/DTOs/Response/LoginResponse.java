package com.TodoLists.DTOs.Response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LoginResponse {
private int id;
private String message;
private String email;
private boolean loginStatus;
private String username;

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
    }
}
