package com.TodoLists.DTOs.Request;

import com.TodoLists.Data.Model.ToDoItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodayTask {
    private List<ToDoItem> allTask;
    private List<ToDoItem> todoTask;
    private List<ToDoItem>  inProgressTask;
    private List<ToDoItem>  completedTask;

}
