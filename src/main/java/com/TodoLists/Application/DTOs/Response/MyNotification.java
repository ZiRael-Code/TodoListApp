package com.TodoLists.Application.DTOs.Response;

import com.TodoLists.Application.Data.Model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class MyNotification {
private List<Notification> data;

}
