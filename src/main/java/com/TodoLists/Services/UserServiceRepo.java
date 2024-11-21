package com.TodoLists.Services;

import com.TodoLists.DTOs.Response.LoginResponse;
import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Data.Model.User;
import com.TodoLists.DTOs.Request.CreateUserRequest;
import com.TodoLists.DTOs.Request.LoginRequest;
import com.TodoLists.DTOs.Response.MyNotification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceRepo {
    LoginResponse logIn(LoginRequest loinRequest) throws Exception ;
    void logOut(String username) throws Exception ;

    User findUserById(int userId) throws Exception;

    void deleteAccount(CreateUserRequest createUserRequest) throws Exception;
    LoginResponse createAccount(CreateUserRequest createUserRequest) throws Exception ;



    MyNotification myNotifications(int id) throws Exception;

}
