package com.TodoLists.DTOs.Response;

import com.TodoLists.Data.Model.ToDoItem;
import lombok.Data;

import java.util.List;
@Data
public class DashBoardResponse {
    private List<String> groups;
    private List<ToDoItem> items;
    private List<List<ToDoItem>> projectGroups;
}
