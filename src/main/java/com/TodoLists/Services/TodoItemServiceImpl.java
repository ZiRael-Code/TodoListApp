package com.TodoLists.Services;

import com.TodoLists.Data.Repository.TodoItemRepo;
import com.TodoLists.DTOs.Request.*;
import com.TodoLists.DTOs.Response.FindTasksResponse;
import com.TodoLists.Data.Model.*;
import com.TodoLists.DTOs.UpdateAllMap;
import com.TodoLists.Data.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    @Autowired
     private TodoItemRepo todoItemRepo;
    @Autowired
     private  UserServiceImpl userService;
    @Autowired
     private UserRepo userRepo;
    @Autowired
    NotificationServie notificationServie;
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
        notificationServie.completedTask(item.getTaskName());
        todoItemRepo.save(item);
    }


    public void addTask(AddTaskRequest addReq) throws Exception {
        User user11 = userService.findUserById(addReq.getUserId());
        System.out.println(user11.getId() +" Is the id user  found");
        if (user11.isEnable()) {
            ToDoItem newTask = new ToDoItem();
            newTask.setTitle(addReq.getTitle());
            newTask.setDescription(addReq.getDescription());
            newTask.setPriority(Priority.valueOf(addReq.getPriority()));
            newTask.setStartDate(getDate(addReq.getStartDate()));
            LocalDate date = getDate(addReq.getEndDate());
            newTask.setDueDate(date);
            newTask.setUserId(addReq.getUserId());
//            LocalDateTime time = stripRealTimerFromTimerType();
            newTask.setStartTimer(addReq.getStartHour());

            newTask.setTaskType(verifyEnum(addReq.getTaskType().strip().replace(' ', '_'), userService.findUserById(addReq.getUserId())));
            List<Notification> notifications = user11.getMyNotification();
            Notification notification = notificationServie.newTask(user11.getUsername(), newTask);
            notifications.add(notification);
            System.out.println("The notification is " + notification);
            user11.setMyNotification(notifications);
            userRepo.save(user11);
            todoItemRepo.save(newTask);
        } else {
            throw new Exception("User not logged in ");
        }
    }

    private LocalDateTime stripRealTimerFromTimerType() {

        return null;
    }

    public String verifyEnum(String enumC,User user) throws Exception {
        List<String> taskType = user.getTaskCategory();
        Arrays.stream(TaskType.values()).forEach(taskType1 -> taskType.add(taskType1.toString()));
        return taskType.stream().toList()
              .stream().toString().equals(enumC) ? TaskType.valueOf(enumC).toString()
              : addingToTasType(enumC, user);
    }


    public  String addingToTasType(String addableEnum, User userId) throws Exception {
        List<String> added = userId.getTaskCategory();
        added.add(addableEnum.toUpperCase().strip().replace(' ', '-'));
        userId.setTaskCategory(added);
        userRepo.save(userId);
        return added.get(added.size()-1);
    }

    public static LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
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

    public Dashboard getDashboardPackage(int userTask) throws Exception {
        List<HashMap<String, String>> lists = new ArrayList<>();
        List<ToDoItem> toDoItems = todoItemRepo.findAllUserTask(userTask);
        Dashboard dashboard = new Dashboard();
        List<String> taskType = userService.findUserById(userTask).getTaskCategory();
        Arrays.stream(TaskType.values()).forEach(taskType1 -> taskType.add(taskType1.toString()));

        for (String s : taskType) {
//            List<ToDoItem> items = new ArrayList<>();
            int itemSize = 0;
            HashMap<String, String> value = new HashMap<>();
            String projName =s;
            int  completedTaskInProj = 0;
            for (ToDoItem toDoItem : toDoItems) {
                if (toDoItem.getTaskType().equals(s)) {
                    itemSize++;
//                    items.add(toDoItem);
                    if (toDoItem.isCompleted()){
                        completedTaskInProj+=1;
                    }
                }
            }
            int completedTask;
            try {
                completedTask = (completedTaskInProj /itemSize)*100;
            }catch (ArithmeticException a){
                completedTask = 0;
            }

            value.put("taskSizeInProject", String.valueOf(itemSize));
            value.put("totalCompletedTask", String.valueOf(completedTask));
            value.put("projectName", projName.replace('_',' '));


            lists.add(value);
        }

        List<ToDoItem> todayItem = getTodayTask(userTask).getItems();
        dashboard.setTodayItem(todayItem);
        int totalSize = todayItem.size();
        int[] completed = {1};
        todayItem.forEach(x->{
            if (x.isCompleted()){
                completed[0]+=1;
            }
        });
        dashboard.setArrangedItems(lists);
        try {
            int total = (completed[0] / totalSize) * 100;
            dashboard.setTotalCompleted(total);
        }catch (RuntimeException e){
            dashboard.setTotalCompleted(0);
        }

//        dashboard.setItemsGroups(Arrays.stream(TaskType.values()).map(x->String.valueOf(x).replace('_', ' ')).toList());

        return dashboard;
    }

    public List<ToDoItem> findTaskGroup(GetProjectGroupReq getProjectGroupReq) throws Exception {
        List<ToDoItem> lists = new ArrayList<>();
        List<ToDoItem> toDoItems = todoItemRepo.findAllUserTask(getProjectGroupReq.getUserId());
        for (ToDoItem toDoItem : toDoItems) {
            if (Objects.equals(toDoItem.getTaskType(), getProjectGroupReq.getTaskType())) {
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
                getDate(get.get("startDate")),
                getDate(get.get("endDate")),
                verifyEnum(get.get("taskType"), userService.findUserById(updateAllRequest.getUserId())));
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
                                                         String type) throws Exception {
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


