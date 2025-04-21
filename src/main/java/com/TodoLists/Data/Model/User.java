package com.TodoLists.Data.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.beans.Transient;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, List<Notification>> myNotification = new HashMap<>();
//    @JsonDeserialize
//    @JsonSerialize
    private List<Map<String, String>> taskCategory = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", taskCategory=" + taskCategory +
                '}';
    }
}
