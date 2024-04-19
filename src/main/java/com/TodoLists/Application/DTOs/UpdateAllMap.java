package com.TodoLists.Application.DTOs;

import com.TodoLists.Application.Data.Model.Priority;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.Services.TodoItemService;

import java.util.Map;

public class UpdateAllMap {
        public ToDoItem updateFromMap(Map<String, String> updateMap, ToDoItem given) throws Exception {
            for (Map.Entry<String, String> entry : updateMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value != null && !value.toString().isEmpty()) {
                    switch (key) {
                        case "title":
                            given.setTitle(value.toString());
                            break;
                        case "description":
                            given.setDescription(value.toString());
                            break;
                        case "startDate":
                            given.setStartDate(TodoItemService.getDate(value.toString()));
                            break;
                         case "endDate":
                            given.setDueDate(TodoItemService.getDate(value.toString()));
                            break;
                        case "priority":
                            given.setPriority(Priority.valueOf(value.toString()));
                            break;
                         case "taskType":
                            given.setTaskType(TodoItemService.verifyEnum(value.toString()));
                            break;
                    }
                }
            }
            return given;
        }
    }

