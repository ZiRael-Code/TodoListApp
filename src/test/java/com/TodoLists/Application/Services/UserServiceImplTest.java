package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.AddTaskRequest;
import com.TodoLists.Application.DTOs.CreateUserRequest;
import com.TodoLists.Application.Data.Model.Priority;
import com.TodoLists.Application.Data.Repository.UserRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
        userService.createAccount(createUserRequest);
        int userId = 1;
//        assertEquals(1, userService.findAllUserTask(userId));
        assertEquals(userId, userRepo.findById(1).getId());
    }

    @Test
    void logIn() throws Exception {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("hello");
        createUserRequest.setPassword("hello123");
        userService.createAccount(createUserRequest);
        userService.logIn(createUserRequest);
        assertTrue(userRepo.findByUsername(createUserRequest.getUsername()).isEnable());
    }

    @Test
    void logOut() throws Exception {
        userService.logOut("hello");
        assertFalse(userRepo.findByUsername("hello").isEnable());
    }

    @Test
    void deleteAccount() throws Exception {
        assertEquals(2, userRepo.findAll().size());
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("hello");
        createUserRequest.setPassword("hello123");
        userService.deleteAccount(createUserRequest);
        assertEquals(1, userRepo.findAll().size());

    }

    @Test
    void addTask() throws Exception {
        AddTaskRequest newTask = new AddTaskRequest();
        newTask.setTitle("Test Task");
        newTask.setDescription("Still need to do html");
        newTask.setPriority(Priority.MEDIUM);
        newTask.setDueDate(LocalDate.now());
        int userId = 1;
        newTask.setUserId(userId);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("hello");
        createUserRequest.setPassword("hello123");
        userService.logIn(createUserRequest);

        userService.addTask(newTask);
    }

    @Test
    void getAllTasks() throws Exception {
        AddTaskRequest newTask = new AddTaskRequest();
        newTask.setTitle("Test Task");
        newTask.setDescription("Still need to do html");
        newTask.setPriority(Priority.MEDIUM);
        newTask.setDueDate(LocalDate.now());
        newTask.setUserId(1);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("hello");
        createUserRequest.setPassword("hello123");

        userService.logIn(createUserRequest);
        userService.addTask(newTask);

        assertEquals(2, userRepo.findByUsername("hello").getMyTask().size());
    }

@Test
    void updateTitle_Description(){

}
}
