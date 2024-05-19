package com.TodoLists.Services;

import com.TodoLists.Application.DTOs.Request.*;
import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.DTOs.Request.*;
import org.TodoLists.Application.DTOs.Request.*;
import com.TodoLists.DTOs.Response.FindTasksResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoItemService {

void updateTitle(UpdateTask updateTask) throws Exception ;
    void markItemASComplete(int userId, int todoItemIds) throws Exception;
    void addTask(AddTaskRequest addReq) throws Exception;
    ToDoItem getTask(int taskId, int userId) throws Exception;

    void deleteTask(int userId, int todoItemIds);

    List<ToDoItem> getAllTasks(int userId) throws Exception;

    AllProGroup getListOfAllProjectGroupTaskCategory(int userTask) throws Exception;
    List<ToDoItem> findTaskGroup(GetProjectGroupReq getProjectGroupReq) throws Exception;

    FindTasksResponse getTodayTask(int userId) throws Exception;
    FindTasksResponse findByDate(String date, int userId) throws Exception;
    void updateAll(UpdateAllRequest updateAllRequest) throws Exception;

}
