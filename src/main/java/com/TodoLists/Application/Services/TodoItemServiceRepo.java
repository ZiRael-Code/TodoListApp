package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.AddTaskRequest;
import com.TodoLists.Application.DTOs.Request.UpdateTask;
import com.TodoLists.Application.Data.Model.ToDoItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoItemServiceRepo {

void updateTitle(UpdateTask updateTask) throws Exception ;
void updateDescription(UpdateTask updateTask) throws Exception;
    void markItemASComplete(int userId, int todoItemIds, boolean mark) throws Exception;
    void addTask(AddTaskRequest addReq) throws Exception;
    ToDoItem getTask(int taskId, int userId) throws Exception;

    void deleteTask(int userId, int todoItemIds);

    List<ToDoItem> getAllTasks(int userId) throws Exception;
}
