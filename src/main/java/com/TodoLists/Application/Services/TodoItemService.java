package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.Request.*;
import com.TodoLists.Application.DTOs.Response.FindTasksResponse;
import com.TodoLists.Application.Data.Model.Priority;
import com.TodoLists.Application.Data.Model.TaskType;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.Data.Model.User;
import com.TodoLists.Application.Data.Repository.TodoItemRepo;
import com.TodoLists.Application.Data.Repository.UserRepo;
import com.TodoLists.Application.DTOs.UpdateAllMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
//@AllArgsConstructor
public class TodoItemService implements TodoItemServiceRepo {
    @Autowired
    private TodoItemRepo todoItemRepo;
    @Autowired
    private UserRepo userRepo;
    UpdateAllMap updateAllMap = new UpdateAllMap();

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
    public void markItemASComplete(int userId, int todoItemIds) throws Exception {
        ToDoItem item = todoItemRepo.findTask(todoItemIds, userId);
        item.setCompleted(true);
        todoItemRepo.save(item);
    }


    public void addTask(AddTaskRequest addReq) throws Exception {
        User user11 = userRepo.findById(addReq.getUserId());
        if (user11.isEnable()) {
            ToDoItem newTask = new ToDoItem();
            newTask.setTitle(addReq.getTitle());
            newTask.setDescription(addReq.getDescription());
            newTask.setPriority(Priority.valueOf(addReq.getPriority()));
            newTask.setStartDate(getDate(addReq.getStartDate()));
            LocalDate date = getDate(addReq.getEndDate());
            newTask.setDueDate(date);
            newTask.setUserId(addReq.getUserId());

            newTask.setTaskType(verifyEnum(addReq.getTaskType().strip().replace(' ', '_')));

            todoItemRepo.save(newTask);
        } else {
            throw new Exception("User not logged in ");
        }
    }

    public static TaskType verifyEnum(String enumC) throws Exception {
      return Arrays.stream(TaskType.values()).toList()
              .stream().toString().equals(enumC) ? TaskType.valueOf(enumC)
              : addingToTasType(enumC);
    }

    public static TaskType addingToTasType(String addableEnum) throws Exception {
        if (addableEnum.length() > 25) throw new RuntimeException("Such task type is not allowed here");
        String fileName = "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\Data\\Model\\TaskType.java";

        String fileInfo = null;
        try( FileInputStream fileInputStream = new FileInputStream(fileName)){
            fileInfo = new String(fileInputStream.readAllBytes()).replace('}', ' ')+',';
        }catch (IOException e){
            throw new Exception(e.getMessage());
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            String toWrite = fileInfo + "\n\t"+addableEnum+"}";
            fileOutputStream.write(toWrite.getBytes());
        }catch (IOException e){
           throw new Exception(e.getMessage());
        }
        return  TaskType.values()[TaskType.values().length-1];
    }

    public static LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
        return LocalDate.parse(date, formatter);
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
            for (ToDoItem toDoItem : toDoItems) {
                if (toDoItem.getTaskType() == TaskType.values()[i]) {
                    items.add(toDoItem);
                }
            }
            lists.add(items);
        }
        allProGroup.setItems(lists);
        allProGroup.setGroups(Arrays.stream(TaskType.values()).map(x->String.valueOf(x).replace('_', ' ')).toList());
        return allProGroup;
    }

    public List<ToDoItem> findTaskGroup(GetProjectGroupReq getProjectGroupReq) throws Exception {
        List<ToDoItem> lists = new ArrayList<>();
        List<ToDoItem> toDoItems = todoItemRepo.findAllUserTask(getProjectGroupReq.getUserId());
        for (ToDoItem toDoItem : toDoItems) {
            if (Objects.equals(toDoItem.getTaskType().toString(), getProjectGroupReq.getTaskType())) {
                lists.add(toDoItem);
            }
        }
        return lists;
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

    public FindTasksResponse findByDate(String date, int userId) throws Exception {
        LocalDate dateFormat = getDate(date);
        List<ToDoItem> itemFound = new ArrayList<>();
        List<ToDoItem> toDoItemList = todoItemRepo.findAllUserTask(userId);
        toDoItemList
                .stream()
                .filter((x)->x.getDueDate()==dateFormat)
                .forEach(itemFound::add);
        FindTasksResponse findTasksResponse = new FindTasksResponse(itemFound);
         return itemFound.isEmpty() ? getTodayTask(userId) : findTasksResponse;
    }

    @Override
    public void updateAll(UpdateAllRequest updateAllRequest) throws Exception {
        HashMap<String, String>  get = updateAllRequest.getRequestMap();
        ToDoItem task = taskBy_StartDate_EndDate_TaskType(
                updateAllRequest.getUserId(),
                TodoItemService.getDate(get.get("startDate")),
                TodoItemService.getDate(get.get("endDate")),
                TodoItemService.verifyEnum(get.get("taskType")));
        if (task!=null) {
            if (!task.isCompleted()) {
                ToDoItem item = updateAllMap.updateFromMap(updateAllRequest.getRequestMap(), task);
                todoItemRepo.save(item);
            }
        }
    }
    ToDoItem taskBy_StartDate_EndDate_TaskType(int userId,
                                                         LocalDate s,
                                                         LocalDate e,
                                                         TaskType type) throws Exception {
        List<ToDoItem> task = todoItemRepo.findAllUserTask(userId);
        final ToDoItem[] taskFound = new ToDoItem[1];
         task.stream().
                 filter(x->
                          x.getStartDate()
                         .equals(s) && x.getDueDate()
                         .equals(e) && x.getTaskType()
                         .equals(type))
                 .forEach(x-> taskFound[0] =x);
        return taskFound[0];
    }

}


