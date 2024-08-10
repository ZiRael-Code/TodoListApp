package com.TodoLists.DTOs.Request;

import com.TodoLists.Data.Model.ToDoItem;
import lombok.Data;

import java.util.List;

@Data
public class FindTaskPackage {
    private List<ToDoItem> allTask;
}
