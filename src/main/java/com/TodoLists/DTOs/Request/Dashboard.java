package com.TodoLists.DTOs.Request;

import com.TodoLists.Data.Model.ToDoItem;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Dashboard {
//    private List<String> itemsGroups;
    private List<Map<String, String>> groupedTask;
//    private  List<ToDoItem> todayTask;
    private int totalCompleted;
    private List<ToDoItem> allTasks;
}
