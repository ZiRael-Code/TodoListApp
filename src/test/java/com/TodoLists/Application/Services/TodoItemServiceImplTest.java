package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.AddTaskRequest;
import com.TodoLists.Application.DTOs.Request.LoginRequest;
import com.TodoLists.Application.DTOs.Request.UpdateAllRequest;
import com.TodoLists.Application.Data.Model.Priority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TodoItemServiceImplTest {
@Autowired
TodoItemServiceImpl todoItemServiceImpl;
    @Autowired
UserServiceImpl userService;
    @Test
    void addTask() throws Exception {
        AddTaskRequest newTask = new AddTaskRequest();
        newTask.setTitle("Test Task");
        newTask.setDescription("Still need to do html");
        newTask.setPriority(String.valueOf(Priority.MEDIUM));
        newTask.setEndDate(String.valueOf(LocalDate.now()));
        newTask.setUserId(2);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("hello");
        loginRequest.setPassword("hello123");
        userService.logIn(loginRequest);
        todoItemServiceImpl.addTask(newTask);

        assertEquals(3, todoItemServiceImpl.getAllTasks(2).size());
    }

    @Test
    void getAllTasks() throws Exception {
       assertEquals(0, todoItemServiceImpl.getAllTasks(2).size());
//       for (ToDoItem i : todoItemService.getAllTasks(1)){
//           System.out.println(i.getTodoItemId());
//       }
    }

    @Test
    void getTask() throws Exception {
        assertNotNull(todoItemServiceImpl.getTask(2, 1));
    }

    @Test
    void updateTitle() {
    }

    @Test
    void updateDescription() throws Exception {
//        assertEquals("test",todoItemService.getAllTasks(1).getLast().getTitle());
        UpdateAllRequest updateAllRequest = new UpdateAllRequest();
        updateAllRequest.setId(1);
        HashMap<String, String> value = new HashMap<>();
        value.put("startDate", "4-9-2024");
        value.put("endDate", "4-9-2024");
        value.put("taskType", "DAILY_TASK");
        value.put("title", "Hello");
        value.put("description", "");
        updateAllRequest.setRequestMap(value);
        todoItemServiceImpl.updateAll(updateAllRequest);
//        assertEquals("Hello",todoItemService.getAllTasks(1).getLast().getTitle());

    }

    @Test
    void markItemASComplete() {
    }

    @Test
    void deleteTask() {
    }
}