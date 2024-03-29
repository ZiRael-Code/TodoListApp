package com.TodoLists.Application.DTOs.Request;

import lombok.Data;

@Data
public class GetProjectGroupReq {
    private int userId;
    private String taskType;
}
