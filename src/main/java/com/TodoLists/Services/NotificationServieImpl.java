package com.TodoLists.Services;

import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Data.Model.Notification;
import com.TodoLists.Data.Model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServieImpl implements NotificationServie{
//    public static String newUser

    @Override
    public Map<String, List<Notification>> newUser(User user){
        String message =  String.format("Hello %s welcome to %s%nHere our main goal is to help you manage task better.", user.getUsername(), "");
        String title = "Welcome to Task-Hive";
        Notification newUserNot = sendNotification(message, title);
        Map<String, List<Notification>> notificationMap = appendNotification(user, title, newUserNot);
        return notificationMap;
    }

    private static Map<String, List<Notification>> appendNotification(User user, String title, Notification newUserNot) {
        Map<String, List<Notification>> notificationMap = user.getMyNotification();

        List<Notification> notifications = notificationMap.get(title);
        if (notifications == null){
            notifications = new ArrayList<>();
        }

        notifications.add(newUserNot);
        notificationMap.put(title, notifications);
        return notificationMap;
    }

    @Override
    public Map<String, List<Notification>> newTask(User user, ToDoItem task)  {
        String message = String.format("Hello %s %nYou just created a new task %s under %s project.", user.getUsername(), task.getTaskName(), task.getTaskType());
        String title = "New Task Created";
        Notification newUserNot = sendNotification(message, title);
        newUserNot.setTaskId(task.getTodoItemId());
         Map<String, List<Notification>> notificationList = appendNotification(user, title, newUserNot);
        return notificationList;
    }
    @Override
    public Map<String, List<Notification>> deleteTask(User user, ToDoItem task)  {
       String message = String.format("Hello %s %nYou just deleted %s task under %s project.", user.getUsername(), task.getTaskName(), task.getTaskType().get("typeName"));
        String title = "Task deleted";
        Map<String, List<Notification>> notificationList = appendNotification(user, title, sendNotification(message, title));

        return notificationList;
    }

    @Override
    public void completedTask(String taskName) {
    }

    public Map<String, List<Notification>> closeToDueDate(User user){
        String message = (String.format("Hello, your task due date is tomorrow.%nIf the you have completed the task kindly mark it as completed.\nthank you.,"));
        String title = "Task date is Close to due Date";
        Map<String, List<Notification>> notificationsMap = user.getMyNotification();
        if (notificationsMap != null) {
            List<Notification> notifications = new ArrayList<>();
            for (ToDoItem item : user.getMyTask()) {
                if (item.getDueDate().toLocalDate().minusDays(1).equals(LocalDate.now())) {
                    for (Notification not : notificationsMap.get(title)) {
                        if (not.getTaskId() != item.getTodoItemId()) {
                            message = (String.format("Hello, your task %s due date is tomorrow.%nIf the you have completed the task kindly mark it as completed.\nthank you.,",item.getTaskName() ));
                            Notification notification = sendNotification(message, title);
                            Map<String, List<Notification>> notAppender = appendNotification(user, title, notification);
                            notifications.add(notAppender.get(title).get(notAppender.get(title).size()-1));
                        }
                    }
                }
            }
            notificationsMap.put(title, notifications);
            return notificationsMap;
        }else {
            Notification notification = sendNotification(message, title);
            Map<String, List<Notification>> notAppender = appendNotification(user, title, notification);
            return  notAppender;
        }
    }

    @Override
    public String deleteNotification(User user) {
        return "";
    }


    public Map<String, List<Notification>> closeToStartDate(User user){
        String message = (String.format("Hello, your task Start date is tomorrow.%nIf the you have completed the task kindly update the end date.\nthank you.," ));
        String title = "Task date is Close to Start Date";
        Map<String, List<Notification>> notificationsMap = user.getMyNotification();
        if (notificationsMap != null) {
            List<Notification> notifications = new ArrayList<>();
            for (ToDoItem item : user.getMyTask()) {
                if (item.getStartDate().toLocalDate().minusDays(1).equals(LocalDate.now())) {
                    for (Notification not : notificationsMap.get(title)) {
                        if (not.getTaskId() != item.getTodoItemId()) {
                            message = (String.format("Hello, your task %s Start date is tomorrow.%nIf the you have completed the task kindly update the end date.\nthank you.,",item.getTaskName() ));
                            Notification notification = sendNotification(message, title);
                            Map<String, List<Notification>> notAppender = appendNotification(user, title, notification);
                            notifications.add(notAppender.get(title).get(notAppender.get(title).size()-1));
                        }
                    }
                }
            }
            notificationsMap.put(title, notifications);
            return notificationsMap;
        }else {
            Notification notification = sendNotification(message, title);
            Map<String, List<Notification>> notAppender = appendNotification(user, title, notification);
            return  notAppender;
        }
    }

    public LocalDateTime getCurrentTime(){
        return LocalDateTime.now();
    }

    Notification sendNotification(String message, String title){
        Notification notification = new Notification();
        notification.setFrom(String.format("From %s", "ZiReal Tech"));
        notification.setNotificationTitle(title);
        notification.setNotificationBody(message);
        return notification;
    }

}
