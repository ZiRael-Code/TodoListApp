package com.TodoLists.Application.DTOs.Request;

import com.TodoLists.Application.Data.Model.ToDoItem;
import lombok.Data;

import java.util.List;
@Data
public class AllProGroup {
    private List<String> groups;
    private List<List<ToDoItem>> items;
}
