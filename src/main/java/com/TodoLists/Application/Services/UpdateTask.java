package com.TodoLists.Application.Services;

import lombok.Data;

@Data
public class UpdateTask {
    private int userId;
    private int taskId;
    private String item;
}
