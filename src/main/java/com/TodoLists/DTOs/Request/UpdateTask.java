package com.TodoLists.DTOs.Request;

import lombok.Data;

@Data
public class UpdateTask {
    private int userId;
    private int taskId;
    private String item;
}
