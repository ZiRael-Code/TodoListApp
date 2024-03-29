package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.*;
import com.TodoLists.Application.DTOs.Response.FindTasksResponse;
import com.TodoLists.Application.Data.Model.Priority;
import com.TodoLists.Application.Data.Model.TaskType;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.Data.Model.User;
import com.TodoLists.Application.Data.Repository.TodoItemRepo;
import com.TodoLists.Application.Data.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        ToDoItem item = todoItemRepo.findTask(updateTask.getTaskId(), updateTask.getUserId());
        item.setDescription(updateTask.getItem());
        todoItemRepo.save(item);
    }

    @Override
    public void markItemASComplete(int userId, int todoItemIds) throws Exception {
        ToDoItem item = todoItemRepo.findTask(todoItemIds, userId);
        item.setCompleted(true);
        todoItemRepo.save(item);
    }


    public void addTask(AddTaskRequest addReq) throws Exception {
        User user11 = userRepo.findById(addReq.getUserId());
        List<ToDoItem> list = user11.getMyTask();
        if (user11.isEnable()) {
            ToDoItem newTask = new ToDoItem();
            newTask.setTitle(addReq.getTitle());
            newTask.setDescription(addReq.getDescription());
            newTask.setPriority(Priority.valueOf(addReq.getPriority()));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
                LocalDate date = LocalDate.parse(addReq.getDueDate(), formatter);
                newTask.setDueDate(date);
//            newTask.setDueDate(LocalDate.parse(addReq.getDueDate()));
            newTask.setUserId(addReq.getUserId());
            newTask.setTaskType(TaskType.valueOf(addReq.getTaskType()));
            todoItemRepo.save(newTask);
        } else {
            throw new Exception("User not logged in ");
        }
    }

    public ToDoItem getTask(int taskId, int userId) throws Exception {

        if (todoItemRepo.findTask(taskId, userId) == null) {
            for (ToDoItem t : todoItemRepo.findAllUserTask(userId)) {
                System.out.println(t.getTodoItemId());
            }
            return null;
        } else {
            return todoItemRepo.findTask(taskId, userId);
        }
    }

    @Override
    public void deleteTask(int userId, int todoItemIds) {

    }

    public AllProGroup getListOfAllProjectGroupTaskCategory(int userTask) throws Exception {
        List<List<ToDoItem>> lists = new ArrayList<>();
        List<ToDoItem> toDoItems = todoItemRepo.findAllUserTask(userTask);
        AllProGroup allProGroup = new AllProGroup();
        for (int i = 0; i < TaskType.values().length; i++) {
            List<ToDoItem> items = new ArrayList<>();
            for (int j = 0; j < toDoItems.size(); j++) {
                if (toDoItems.get(j).getTaskType() == TaskType.values()[i]) {
                    items.add(toDoItems.get(j));
                }
            }
            lists.add(items);
        }
        allProGroup.setItems(lists);
        return allProGroup;
    }

    public List<ToDoItem> findTaskGroup(GetProjectGroupReq getProjectGroupReq) throws Exception {
        List<ToDoItem> lists = new ArrayList<>();
        List<ToDoItem> toDoItems = todoItemRepo.findAllUserTask(getProjectGroupReq.getUserId());
//        for (int i = 0; i < TaskType.values().length; i++) {
            for (int j = 0; j < toDoItems.size(); j++) {
                if (Objects.equals(toDoItems.get(j).getTaskType().toString(), getProjectGroupReq.getTaskType())) {
                    lists.add(toDoItems.get(j));
                }
//            }
        }
        return lists;
    }

    public List<GroupTaskDetails> getGroupTaskDetails(int userTask) throws Exception {
        List<GroupTaskDetails> groupTaskDetails = new ArrayList<>();
        AllProGroup find = getListOfAllProjectGroupTaskCategory(userTask);
        for (int i = 0; i < find.getItems().size(); i++) {
            for (int j = 0; j < find.getItems().get(i).size(); j++) {
                GroupTaskDetails groupTaskDetails1 = new GroupTaskDetails(find.getItems().get(i).size(), find.getItems().get(i).get(j).getTaskType().toString(), 0);
                groupTaskDetails.add(groupTaskDetails1);
            }
        }
        return groupTaskDetails;
    }

    public void verify(String Pri) {
    }

    public FindTasksResponse getTodayTask(int userId) throws Exception {
      List<ToDoItem> list =  todoItemRepo.findAllUserTask(userId);
      List<ToDoItem> todayTask = new ArrayList<>();
        for (ToDoItem item : list){
            if (item.getDueDate().isEqual(LocalDate.now())){
                todayTask.add(item);
            }
        }
        return new FindTasksResponse(todayTask);
    }
}


