//package com.TodoLists.Application.Services;
//
//import com.TodoLists.Application.Data.Model.ToDoItem;
//import com.TodoLists.Application.Data.Repository.TodoListRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TodoListService implements TodoListServiceRepo{
//    @Autowired
//    private TodoListRepo todoListRepo;
//
//    public ToDoItem findAllUserTask(int userId) throws Exception {
//        List<ToDoItem> todo = todoListRepo.findAll();
//        for (ToDoItem toDoItem: todo) {
//            if (toDoItem.getUserId() == userId){
//                return toDoItem;
//            }
//        }
//        throw new Exception("User not Exist");
//    }
//
//    public void findTask(int taskId) throws Exception {
//        todoListRepo.findById(taskId);
//    }
//
//    public void deleteTask(int taskIs) throws Exception {
//        todoListRepo.deleteAccountById(taskIs);
//    }
//
//    public void addTask(ToDoItem toDoItem) throws Exception {
//        todoListRepo.save(toDoItem);  // do ur validation here ooo
//    }
//
//}
