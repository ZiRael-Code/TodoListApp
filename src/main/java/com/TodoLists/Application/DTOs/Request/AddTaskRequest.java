package com.TodoLists.Application.DTOs.Request;

import com.TodoLists.Application.Data.Model.Priority;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddTaskRequest {
    private int userId;
    private String title;
    private String description;
    private String dueDate;
    private String priority;
    private String taskType;


}
