package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.AddTaskRequest;
import com.TodoLists.Application.DTOs.Request.LoginRequest;
import com.TodoLists.Application.Data.Model.Priority;
import com.TodoLists.Application.Data.Model.ToDoItem;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TodoItemServiceTest {
@Autowired
TodoItemService todoItemService;
    @Autowired
UserServiceImpl userService;
    @Test
    void addTask() throws Exception {
        AddTaskRequest newTask = new AddTaskRequest();
        newTask.setTitle("Test Task");
        newTask.setDescription("Still need to do html");
        newTask.setPriority(Priority.MEDIUM);
        newTask.setDueDate(LocalDate.now());
        newTask.setUserId(2);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("hello");
        loginRequest.setPassword("hello123");
        userService.logIn(loginRequest);
        todoItemService.addTask(newTask);

        assertEquals(3, todoItemService.getAllTasks(2).size());
    }

    @Test
    void getAllTasks() throws Exception {
       assertEquals(0, todoItemService.getAllTasks(2).size());
//       for (ToDoItem i : todoItemService.getAllTasks(1)){
//           System.out.println(i.getTodoItemId());
//       }
    }

    @Test
    void getTask() throws Exception {
        assertNotNull(todoItemService.getTask(2, 1));
    }

    @Test
    void updateTitle() {
    }

    @Test
    void updateDescription() {
    }

    @Test
    void markItemASComplete() {
    }

    @Test
    void deleteTask() {
    }
}