package com.TodoLists.DTOs.Request;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class GroupTaskDetails {
    private int tasksSize;
    private String projName;
    private int progress;

}
