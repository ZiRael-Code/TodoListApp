package com.TodoLists.DTOs.Request;

import lombok.Data;

import java.util.Map;

@Data
public class AddTaskRequest {
    private int userId;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String priority;
    private Map<String, String> taskType;
}
