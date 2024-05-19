package com.TodoLists.Services;

import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Data.Model.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServieImpl implements NotificationServie{

    @Override
    public Notification newUser(String userName){
        String message =  String.format("Hello %s welcome to %s%nHere our main goal is to help you manage task better.", userName, "");
        String title = "New User Created";
        return sendNotification(message, title);
    }
    @Override
    public Notification newTask(String userName, ToDoItem task)  {
        String message = String.format("Hello %s %nYou just created a new task %s under %s project.", userName, task.getTaskName(), task.getTaskType());
        String title = "Task Created Successfully";
        return sendNotification(message, title);
    }
    @Override
    public Notification deleteTask(String userName, ToDoItem task)  {
       String message = String.format("Hello %s %nYou just deleted %s task under %s project.", userName, task.getTaskName(), task.getTaskType());
        String title = "Task deleted";
        return sendNotification(message, title);
    }

    @Override
    public void completedTask(String taskName) {
    }

    public List<Notification> closeToDueDate(List<ToDoItem> toDoItems){
        List<Notification> myNotifications = new ArrayList<>();
        for(ToDoItem item: toDoItems){
            if (item.getDueDate().minusDays(1).equals(LocalDate.now())){
//                System.out.println();
                String message = (String.format("Hello, your task %s due date is tomorrow.%nIf the you have completed the task kindly mark it as completed.\nthank you.,",item.getTaskName() ));
                String title = "Task date is Close to due Date";
                Notification notification = sendNotification(message, title);
                myNotifications.add(notification);
            }
        }
        return myNotifications;
    }
    public List<Notification> closeToStartDate(List<ToDoItem> toDoItems){
        List<Notification> myNotifications = new ArrayList<>();
        for(ToDoItem item: toDoItems){
            if (item.getStartDate().minusDays(1).equals(LocalDate.now())){
                String message = (String.format("Hello, your task %s due date is tomorrow.%nIf the you have completed the task kindly mark it as completed.%n thank you.,",item.getTaskName() ));
             String title = "Task date is Close to Start Date";
              Notification notification = sendNotification(message, title);
                myNotifications.add(notification);
            }
        }
        return myNotifications;
    }

    public LocalDateTime getCurrentTime(){
        return LocalDateTime.now();
    }

    Notification sendNotification(String message, String title){
        Notification notification = new Notification();
        notification.setNotificationSender(String.format("From %s", ""));
        notification.setNotificationTitle(title);
        notification.setNotificationBody(message);
        return notification;
    }

}
