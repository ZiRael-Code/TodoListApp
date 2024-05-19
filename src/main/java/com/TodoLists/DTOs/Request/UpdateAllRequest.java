package com.TodoLists.DTOs.Request;

import lombok.Data;

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
