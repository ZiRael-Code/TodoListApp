package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.AddTaskRequest;
import com.TodoLists.Application.DTOs.Request.CreateUserRequest;
import com.TodoLists.Application.DTOs.Request.LoginRequest;
import com.TodoLists.Application.DTOs.Response.LoginResponse;
import com.TodoLists.Application.Data.Model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceRepo {
    LoginResponse logIn(LoginRequest loinRequest) throws Exception ;
    void logOut(String username) throws Exception ;
    void deleteAccount(CreateUserRequest createUserRequest) throws Exception;
    LoginResponse createAccount(CreateUserRequest createUserRequest) throws Exception ;

}
