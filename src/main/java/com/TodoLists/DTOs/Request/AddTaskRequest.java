package com.TodoLists.DTOs.Request;

import lombok.Data;

@Data
public class AddTaskRequest {
    private int userId;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String priority;
    private String taskType;
    private String startHour;

}
