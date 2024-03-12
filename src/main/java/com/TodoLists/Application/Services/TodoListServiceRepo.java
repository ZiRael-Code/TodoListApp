package com.TodoLists.Application.Services;

import com.TodoLists.Application.Data.Model.ToDoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoListServiceRepo {

    ToDoItem findAllUserTask(int userId) throws Exception;

    void findTask(int taskId) throws Exception;

    void deleteTask(int taskIs) throws Exception;
    void addTask(ToDoItem toDoItem) throws Exception;

}
