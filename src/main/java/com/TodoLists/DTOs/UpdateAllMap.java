package com.TodoLists.DTOs;

import com.TodoLists.Data.Model.Priority;
import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Services.TodoItemServiceImpl;

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
                            given.setStartDate(TodoItemServiceImpl.getDate(value.toString()));
                            break;
                         case "endDate":
                            given.setDueDate(TodoItemServiceImpl.getDate(value.toString()));
                            break;
                        case "priority":
                            given.setPriority(Priority.valueOf(value.toString()));
                            break;
                         case "taskType":
//                            given.setTaskType(value.toString());
                            break;
                    }
                }
            }
            return given;
        }
    }

