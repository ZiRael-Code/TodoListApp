package com.TodoLists.Services;

import com.TodoLists.DTOs.Request.ForgetPasswordRequest;
import com.TodoLists.DTOs.Response.LoginResponse;
import com.TodoLists.Data.Model.User;
import com.TodoLists.DTOs.Request.CreateUserRequest;
import com.TodoLists.DTOs.Request.LoginRequest;
import com.TodoLists.DTOs.Response.MyNotification;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    LoginResponse logIn(LoginRequest loinRequest) throws Exception ;
    void logOut(int id) throws Exception ;

    User findUserById(int userId) throws Exception;

    void deleteAccount(CreateUserRequest createUserRequest) throws Exception;
    LoginResponse createAccount(CreateUserRequest createUserRequest) throws Exception ;



    MyNotification myNotifications(int id) throws Exception;

    String resetPassword(ForgetPasswordRequest forgetPasswordRequest) throws Exception;

    void deleteAll() throws Exception;
}
