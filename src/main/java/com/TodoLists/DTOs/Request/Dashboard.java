package com.TodoLists.DTOs.Request;

import com.TodoLists.Data.Model.ToDoItem;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
@Data
public class Dashboard {
//    private List<String> itemsGroups;
    private List<HashMap<String, String>> arrangedItems;
    private  List<ToDoItem> todayItem;
    private int totalCompleted;
}
