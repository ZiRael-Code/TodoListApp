package com.TodoLists.Data.Repository;
import com.TodoLists.Data.Model.Priority;
import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Data.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
class TodoItemRepoImpli implements TodoItemRepo{
    @Autowired
    private UserRepo userRepo;
    @Override
    public ToDoItem findTask(int taskId, int userId) throws Exception {
        User user11 =  userRepo.findById(userId);
            List<ToDoItem> item = userRepo.findById(userId).getMyTask();
            for (ToDoItem item1: item) {
                if (item1.getTodoItemId()==taskId){
                    return item1;
                }
            }
        return null;
    }

    public void  save(ToDoItem task) throws Exception {
        ToDoItem task1 = task;
        User user1 = userRepo.findById(task1.getUserId());
        List<ToDoItem> items = user1.getMyTask();
        int indexoF = -1;
        for (ToDoItem u : items) {
            if (u.getTodoItemId() == task1.getTodoItemId()) {
                indexoF = items.indexOf(u);
                break;
            }
        }

        if (indexoF == -1) {
            int lol = -1;
            if ((items.isEmpty())) {
                lol = 0;
            }else {
                lol =  items.get(items.size()-1).getTodoItemId();
            }
            lol+=1;
            task1.setTodoItemId(lol);
            items.add(task1);

        } else {
            items.set(indexoF, task1);
        }
        user1.setMyTask(items);
        userRepo.save(user1);
        System.out.println(items.get(items.size()-1).getTodoItemId());
    }

    @Override
    public void deleteAll(int userId) throws Exception {
        User user = userRepo.findById(userId);
        user.setMyTask(new ArrayList<>());
       userRepo.save(user);
    }

    @Override
    public ToDoItem findById(int taskId, int userId) throws Exception {
        List<ToDoItem> list = userRepo.findById(userId).getMyTask();
        for (ToDoItem toDoItem : list){
            if (toDoItem.getTodoItemId() == taskId){
                return toDoItem;
            }
        }
        return null;
    }


    @Override
    public List<ToDoItem> findAllUserTask(int userId) throws Exception {
        return userRepo.findById(userId).getMyTask();
    }

    @Override
    public List<ToDoItem> findAllPriority(int userId,Priority priority) throws Exception {
       List<ToDoItem> toDoItems = findAllUserTask(userId);
       List<ToDoItem> newPriority = new ArrayList<>();
       for (ToDoItem toDoItem : toDoItems){
           if (toDoItem.getPriority() == priority){
               newPriority.add(toDoItem);
           }
       }

        return newPriority;
    }


        @Override
    public void deleteTask(int userId, int todoItemIds) throws Exception{
        User user = userRepo.findById(userId);
        List<ToDoItem> toDoItems = user.getMyTask();
        for (ToDoItem toDoItem1 : toDoItems ) {
            if (toDoItem1.getTodoItemId() == todoItemIds){
                toDoItems.remove(toDoItem1);
                break;
            }
        }
        user.setMyTask(toDoItems);
        userRepo.save(user);
    }
}