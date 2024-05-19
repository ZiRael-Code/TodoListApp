package com.TodoLists.DTOs.Request;

import lombok.Data;

@Data
public class GetProjectGroupReq {
    private int userId;
    private String taskType;
}
