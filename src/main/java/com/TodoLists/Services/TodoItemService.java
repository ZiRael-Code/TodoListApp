package com.TodoLists.Services;

import com.TodoLists.DTOs.Response.AppPackage;
import com.TodoLists.DTOs.Response.MobileNavPackage;
import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.DTOs.Request.*;
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

    Dashboard getDashboardPackage(int userTask) throws Exception;
    AppPackage getAppPackage(int userTask) throws Exception;
    List<ToDoItem> findTaskGroup(GetProjectGroupReq getProjectGroupReq) throws Exception;

    FindTasksResponse getTodayTask(int userId) throws Exception;
    FindTasksResponse findByDate(String date, int userId) throws Exception;
    void updateAll(UpdateAllRequest updateAllRequest) throws Exception;

    MobileNavPackage getMobileNavPackage(int id) throws Exception;
}
