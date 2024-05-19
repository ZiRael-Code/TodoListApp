package com.TodoLists.DTOs.Response;

import com.TodoLists.Data.Model.ToDoItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindTasksResponse {
    List<ToDoItem> items;


}
