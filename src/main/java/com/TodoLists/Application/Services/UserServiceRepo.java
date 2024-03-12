package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.AddTaskRequest;
import com.TodoLists.Application.DTOs.CreateUserRequest;
import com.TodoLists.Application.Data.Model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceRepo {
    int logIn(CreateUserRequest createUserRequest) throws Exception ;
    void logOut(String username) throws Exception ;
    void deleteAccount(CreateUserRequest createUserRequest) throws Exception;
    void createAccount(CreateUserRequest createUserRequest) throws Exception ;
    void addTask(AddTaskRequest addReq) throws Exception;
    void updateTitle(UpdateTask updateTask) throws Exception;
    void updateDescription(UpdateTask updateTask) throws Exception;
    void markItemASComplete(int userId, int todoItemIds, boolean mark) throws Exception;
    void deleteTask(int userId, int todoItemIds) throws Exception;
    List<ToDoItem> getAllTasks(int userId) throws Exception;
}
