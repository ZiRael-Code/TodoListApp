package com.TodoLists.Data.Repository;

import com.TodoLists.Data.Model.Priority;
import com.TodoLists.Data.Model.ToDoItem;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TodoItemRepo {
    ToDoItem findTask(int taskId, int userId) throws Exception;
    ToDoItem save(ToDoItem task) throws Exception;
    void deleteAll(int userId) throws Exception;
    ToDoItem findById(int taskId, int userId) throws Exception;
    List<ToDoItem> findAllUserTask( int userId) throws Exception;
    List<ToDoItem> findAllPriority(int userId, Priority priority) throws Exception;
    void deleteTask(int userId, int todoItemIds) throws Exception;
}
