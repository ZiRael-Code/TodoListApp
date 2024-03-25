package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.CreateUserRequest;
import com.TodoLists.Application.DTOs.Request.LoginRequest;
import com.TodoLists.Application.DTOs.Response.LoginResponse;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.Data.Model.User;
import com.TodoLists.Application.Data.Repository.UserRepoImpl;
import com.TodoLists.Application.Data.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserServiceRepo {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private UserRepoImpl user;


    void validate(CreateUserRequest createUserRequest){
        if (createUserRequest.getUsername() == null && createUserRequest.getPassword() == null){
            throw new RuntimeException("Please input the require fields Username, Password");
        }
    }

    @Override
    public LoginResponse createAccount(CreateUserRequest createUserRequest) throws Exception {
        validate(createUserRequest);
        User user1 = new User();
        user1.setEmail(createUserRequest.getEmail());
        user1.setPassword(createUserRequest.getPassword());
        user1.setUsername(createUserRequest.getUsername());
        User user2 = userRepo.save(user1);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user2.getId());
        return loginResponse;
    }

    @Override
    public LoginResponse logIn(LoginRequest loinRequest) throws Exception {
        User user1 = user.findByUsername(loinRequest.getUsername());
        if (user1.equals(userRepo.findByUsernameAndPassword(loinRequest.getUsername(), loinRequest.getPassword()))) {
            user1.setEnable(true);
            user1 = userRepo.save(user1);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(user1.getId());
            loginResponse.setMessage("Login Successful");
            loginResponse.setLoginStatus(user1.isEnable());
            return loginResponse;
        }else {
            throw new RuntimeException("User name or password not correct");
        }
    }

    @Override
    public void logOut(String username) throws Exception {
        User user1 = user.findByUsername(username);
        user1.setEnable(false);
        userRepo.save(user1);
    }

    @Override
    public void deleteAccount(CreateUserRequest createUserRequest) throws Exception {
        validate(createUserRequest);
        User user1 = new User();
        user1.setPassword(createUserRequest.getPassword());
        user1.setUsername(createUserRequest.getUsername());
       userRepo.deleteAccountByUsernameAndPassword(user1);
    }

    public List<User> findAllUser() throws Exception {
        return userRepo.findAll();
    }




}
