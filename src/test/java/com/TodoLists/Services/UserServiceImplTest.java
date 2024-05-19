package com.TodoLists.Services;

import com.TodoLists.DTOs.Request.CreateUserRequest;
import com.TodoLists.DTOs.Request.LoginRequest;
import com.TodoLists.DTOs.Response.LoginResponse;
import com.TodoLists.Data.Model.User;
import com.TodoLists.Data.Repository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepoImpl userRepo;
    @Test
    void createAccount() throws Exception {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("hello");
        createUserRequest.setPassword("hello123");
        createUserRequest.setEmail("");
        LoginResponse loginResponse = userService.createAccount(createUserRequest);
//        assertEquals(1, userService.findAllUserTask(userId));
        System.out.println(loginResponse.getId());
    }

    @Test
    void findAll() throws Exception {
        for (User user :userService.findAllUser()){
            System.out.println(user.getId());
        }
    }
    @Test
    void logIn() throws Exception {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("hello");
        createUserRequest.setEmail("");
        createUserRequest.setPassword("hello123");
        userService.createAccount(createUserRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("betty");
        loginRequest.setPassword("hi");
        ;
        Assertions.assertTrue(userService.logIn(loginRequest).isLoginStatus());
    }

    @Test
    void logOut() throws Exception {
        userService.logOut("hello");
        assertFalse(userRepo.findByUsername("hello").isEnable());
    }

    @Test
    void deleteAccount() throws Exception {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("hello");
        createUserRequest.setPassword("hello123");
        userService.createAccount(createUserRequest);
        userService.deleteAccount(createUserRequest);
        assertEquals(0, userRepo.findAll().size());

    }

//    @Test
//    void addTask() throws Exception {
//        AddTaskRequest newTask = new AddTaskRequest();
//        newTask.setTitle("Test Task");
//        newTask.setDescription("Still need to do html");
//        newTask.setPriority(Priority.MEDIUM);
//        newTask.setDueDate(LocalDate.now());
//        int userId = 1;
//        newTask.setUserId(userId);
//
//        LoginRequest createUserRequest = new LoginRequest();
//        createUserRequest.setUsername("hello");
//        createUserRequest.setPassword("hello123");
//        userService.logIn(createUserRequest);
//
//        userService.addTask(newTask);
//    }

//    @Test
//    void getAllTasks() throws Exception {
//        AddTaskRequest newTask = new AddTaskRequest();
//        newTask.setTitle("Test Task");
//        newTask.setDescription("Still need to do html");
//        newTask.setPriority(Priority.MEDIUM);
//        newTask.setDueDate(LocalDate.now());
//        newTask.setUserId(1);
//
//        LoginRequest createUserRequest = new LoginRequest();
//        createUserRequest.setUsername("hello");
//        createUserRequest.setPassword("hello123");
//
//        userService.logIn(createUserRequest);
//        userService.addTask(newTask);
//
//        assertEquals(2, userRepo.findByUsername("hello").getMyTask().size());
//    }
//
//@Test
//    void updateTitle_Description(){
//
//}
}
