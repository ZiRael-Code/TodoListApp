package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.AddTaskRequest;
import com.TodoLists.Application.DTOs.Request.UpdateTask;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.Data.Model.User;
import com.TodoLists.Application.Data.Repository.TodoItemRepo;
import com.TodoLists.Application.Data.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService implements TodoItemServiceRepo {
    @Autowired
    private TodoItemRepo todoItemRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public void updateTitle(UpdateTask updateTask) throws Exception {
        ToDoItem items = todoItemRepo.findTask(updateTask.getTaskId(), updateTask.getUserId());
        items.setTitle(updateTask.getItem());
        todoItemRepo.save(items);
    }

    public List<ToDoItem> getAllTasks(int userId) throws Exception {
        return todoItemRepo.findAllUserTask(userId);
    }


    @Override
    public void updateDescription(UpdateTask updateTask) throws Exception {
        ToDoItem  item  = todoItemRepo.findTask(updateTask.getTaskId(), updateTask.getUserId());
        item.setDescription(updateTask.getItem());
        todoItemRepo.save(item);
    }

    @Override
    public void markItemASComplete(int userId, int todoItemIds, boolean mark) throws Exception {
        ToDoItem  item  = todoItemRepo.findTask(todoItemIds, userId);
        item.setCompleted(mark);
        todoItemRepo.save(todoItemRepo.findTask(todoItemIds, userId));
//       Class<User> l =  loll(User.class);
    }



    public void addTask(AddTaskRequest addReq) throws Exception {
        User user11 =  userRepo.findById(addReq.getUserId());
        List<ToDoItem> list = user11.getMyTask();
            if (user11.isEnable()){
        ToDoItem newTask = new ToDoItem();
        newTask.setTitle(addReq.getTitle());
        newTask.setDescription(addReq.getDescription());
        newTask.setPriority(addReq.getPriority());
        newTask.setDueDate(addReq.getDueDate());
        newTask.setUserId(addReq.getUserId());
        todoItemRepo.save(newTask);
            }else{
                throw new Exception("User not logged in ");
            }
    }

    public ToDoItem getTask(int taskId, int userId) throws Exception {

       if (todoItemRepo.findTask(taskId, userId) == null){
           for (ToDoItem t: todoItemRepo.findAllUserTask(userId)){
               System.out.println(t.getTodoItemId());
           }return null;
       }else {
           return todoItemRepo.findTask(taskId, userId);
       }
    }

    @Override
    public void deleteTask(int userId, int todoItemIds) {

    }
}