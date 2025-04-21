package com.TodoLists.Services;

import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Data.Model.Notification;
import com.TodoLists.Data.Model.User;

import java.util.List;
import java.util.Map;

public interface NotificationServie {
    Map<String, List<Notification>> newUser(User user);

    Map<String, List<Notification>> newTask(User user,  ToDoItem task);

    Map<String, List<Notification>> deleteTask(User user, ToDoItem item);

    void completedTask(String taskName);
    Map<String, List<Notification>>closeToStartDate(User user);
    Map<String, List<Notification>> closeToDueDate(User user);
    String deleteNotification(User user);

}
