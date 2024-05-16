package com.TodoLists.Application.Services;

import com.TodoLists.Application.Data.Model.Notification;
import com.TodoLists.Application.Data.Model.ToDoItem;

import java.util.List;

public interface NotificationServie {
    Notification newUser(String userName);

    Notification newTask(String userName, ToDoItem task);

    Notification deleteTask(String userName, ToDoItem task);

    void completedTask(String taskName);
    List<Notification> closeToStartDate(List<ToDoItem> toDoItems);
    List<Notification> closeToDueDate(List<ToDoItem> toDoItems);
}
