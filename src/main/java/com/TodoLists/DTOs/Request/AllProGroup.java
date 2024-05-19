package com.TodoLists.DTOs.Request;

import com.TodoLists.Data.Model.ToDoItem;
import lombok.Data;

import java.util.List;
@Data
public class AllProGroup {
    private List<String> groups;
    private List<List<ToDoItem>> items;
}
