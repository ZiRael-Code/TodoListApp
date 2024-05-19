package com.TodoLists.Services;

import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Data.Model.Notification;

import java.util.List;

public interface NotificationServie {
    Notification newUser(String userName);

    Notification newTask(String userName, ToDoItem task);

    Notification deleteTask(String userName, ToDoItem task);

    void completedTask(String taskName);
    List<Notification> closeToStartDate(List<ToDoItem> toDoItems);
    List<Notification> closeToDueDate(List<ToDoItem> toDoItems);
}
