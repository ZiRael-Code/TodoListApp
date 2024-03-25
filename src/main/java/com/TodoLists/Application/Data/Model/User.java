package com.TodoLists.Application.Data.Model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
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

        public int getCurrentId(){
            setCurrentId(getId());
            if (currentId == -1){
                throw new RuntimeException("User not logged in");
            }else {
                return currentId;
            }
        }

}
