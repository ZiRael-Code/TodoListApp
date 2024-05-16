package com.TodoLists.Application.Data.Model;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@JsonDeserialize
@JsonSerialize
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String email;
    private String username;
    private String password;
    private boolean enable = false;
    private List<ToDoItem> myTask = new ArrayList<>();
    private int currentId = -1;
    private List<Notification> myNotification = new ArrayList<>();
    private List<String> taskCategory = new ArrayList<>();



}
