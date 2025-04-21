package com.TodoLists.DTOs.Response;

import com.TodoLists.Data.Model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class MyNotification {
private Map<String, List<Notification>> data;

}
