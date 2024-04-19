package com.TodoLists.Application.DTOs.Request;

import com.TodoLists.Application.Data.Model.Priority;
import com.TodoLists.Application.Data.Model.TaskType;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;

@Data
public class UpdateAllRequest {
    private int id;
    private int userId;

    HashMap<String, String> requestMap;
//    private String title;
//    private String description;
//    private String startDate;
//    private String dueDate;
//    private String priority;
//    private String taskType;

}
