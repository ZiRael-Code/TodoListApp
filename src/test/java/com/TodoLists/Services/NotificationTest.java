package com.TodoLists.Services;

import com.TodoLists.Data.Model.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class NotificationTest {
    @Autowired
    TodoItemService todoItemService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    NotificationServie notificationServie;
    @Test
    void closeToDueDate() throws Exception {
//        System.out.println("userService.myNotifications(1) = " + userService.myNotifications(1));
//        System.out.println(todoItemService.findByDate("2024-5-14", 1));
        List<Notification> text = new ArrayList<>();
        List<Notification> p = (notificationServie.closeToStartDate(userService.findUserById(1).getMyTask()));
    text.add(p.stream().iterator().next());
        System.out.println(userService.myNotifications(1));
    }
}
