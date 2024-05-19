package com.TodoLists.DTOs.Response;

import com.TodoLists.Data.Model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class MyNotification {
private List<Notification> data;

}
