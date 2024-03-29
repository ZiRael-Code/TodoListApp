package com.TodoLists.Application.Data.Repository;

import com.TodoLists.Application.Data.Model.ToDoItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TodoItemRepoImpliTest {
    @Autowired
TodoItemRepo todoItemRepo;

    @Test
    void findAllUserTask() throws Exception {
        System.out.println(todoItemRepo.findAllUserTask(1).toString());
    }

    @Test
    void saveTodoItemText() throws Exception {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setUserId(1);
        todoItemRepo.save(toDoItem);
        assertEquals(1,  todoItemRepo.findAllUserTask(1).size());

    }

    @Test
    void deleteAll() throws Exception {
        todoItemRepo.deleteAll(1);
        assertEquals(0,  todoItemRepo.findAllUserTask(1).size());
    }

    @Test
    void findById() throws Exception {
        assertNotNull(todoItemRepo.findTask(1,1));
    }

    @Test
    void findAllPriority() {

    }

    @Test
    void deleteTask() throws Exception {
        todoItemRepo.deleteTask(1, 1);
        assertEquals(0, todoItemRepo.findAllUserTask(1).size());
    }
}