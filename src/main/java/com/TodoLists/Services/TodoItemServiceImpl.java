package com.TodoLists.Services;

import com.TodoLists.DTOs.Response.AppPackage;
import com.TodoLists.DTOs.Response.MobileNavPackage;
import com.TodoLists.DTOs.Response.TodayTask;
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
import java.time.LocalTime;
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
        List<ToDoItem> allTask = todoItemRepo.findAllUserTask(userId);
        for (ToDoItem item : allTask) {
            if (item.getStartDate().isAfter(LocalDateTime.now())) {
                // Start time is in the future
                item.setTaskStatus(TaskStatus.TODO);
            } else if (item.getStartDate().isBefore(LocalDateTime.now()) && item.getDueDate().isAfter(LocalDateTime.now())) {
                // Start time is in the past and end time is in the future
                item.setTaskStatus(TaskStatus.INPROGRESS);
            } else if (item.getDueDate().isBefore(LocalDateTime.now())) {
                // End time is in the past
                item.setTaskStatus(TaskStatus.COMPLETED);
            }
            todoItemRepo.save(item);
        }
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
            LocalDateTime date = getDate(addReq.getEndDate());
            newTask.setDueDate(date);
            newTask.setUserId(addReq.getUserId());
            List<Notification> notifications = user11.getMyNotification();
            Notification notification = notificationServie.newTask(user11.getUsername(), newTask);
            notifications.add(notification);
//            System.out.println("The notification is " + notification);
            user11.setMyNotification(notifications);
            List<Map<String, String>> types = user11.getTaskCategory();
            if ((verifyEnum(addReq.getTaskType(),
                    userService.findUserById(addReq.getUserId())))){
                newTask.setTaskType(addReq.getTaskType().get("typeName"));
            }else {
                newTask.setTaskType(addReq.getTaskType().get("typeName"));
                types.add(addReq.getTaskType());
            }

           ToDoItem item = todoItemRepo.save(newTask);
           List<ToDoItem> task = user11.getMyTask();
           task.add(item);
           user11.setMyTask(task);
            System.out.println("+++++++=-)(((*&^(just addit to user in todo impli before"+user11);
            userRepo.save(user11);

        } else {
            throw new Exception("User not logged in ");
        }
    }

    private LocalDateTime stripRealTimerFromTimerType() {

        return null;
    }

    public boolean verifyEnum(Map<String, String> enumC, User user) throws Exception {
        List<Map<String, String>> taskType = user.getTaskCategory();
        var index = -1;
        for (Map<String,String> type: taskType) {
            if (type.get("typeName").equals(enumC.get("typeName"))){
                index = 1;
                break;
            }
        }
        if (index == 1){
            return true;
        }else {
           taskType.add(enumC);
           user.setTaskCategory(taskType);
          User ac = userRepo.save(user);
            System.out.println("saved userd_+_+_+__+_+_+_)/0" + ac);

            return false;
        }
    }


    public   Map<String, String> addingToTaskType(Map<String, String> addableEnum, User userId) throws Exception {
        List<Map<String, String>> added = userId.getTaskCategory();
        added.add(addableEnum);
        userId.setTaskCategory(added);
        User user = userRepo.save(userId);
//        System.out.println(user + "__________________\n added task type successful");
        userRepo.save(user);
        return added.get(added.size() - 1);
    }

    public static LocalDateTime getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

    public static void main(String[] args) {
        String date = "2024-08-14T17:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dates = LocalDateTime.parse(date, formatter);
        System.out.println(dates);
        //
//        String time = "05:16";
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("HH:mm");
//        System.out.println(LocalDate.now());
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
        Dashboard dashboard = new Dashboard();

        User user = userService.findUserById(userTask);
        List<Map<String, String>> groupedTasks = new ArrayList<Map<String, String>>();
        int completedTaskInProj = 0;

        for (Map<String, String> taskType : user.getTaskCategory()){
            Map<String, String> taskGroup = new HashMap<String, String>();
            Map<String, String> taskTypeMap = taskType;
            int itemSize = 0;
            for (ToDoItem toDoItem : user.getMyTask()) {
                if (toDoItem.getTaskType().equalsIgnoreCase(taskType.get("typeName"))) {
                    itemSize++;
                }
                if (toDoItem.isCompleted()){
                    completedTaskInProj+=1;
                }
            }
                taskGroup.put("typeName", taskTypeMap.get("typeName"));
                taskGroup.put("color", taskTypeMap.get("color"));
                taskGroup.put("icon", taskTypeMap.get("icon"));
            taskGroup.put("taskSizeInProject", String.valueOf(itemSize));
            groupedTasks.add(taskGroup);
          }
        dashboard.setTotalCompleted(completedTaskInProj);
        dashboard.setGroupedTask(groupedTasks);
//        dashboard.setTodayTask(getTodayTask(userTask));
        dashboard.setAllTasks(user.getMyTask());



        //        List<HashMap<String, String>> lists = new ArrayList<>();
//        List<ToDoItem> toDoItems = todoItemRepo.findAllUserTask(userTask);
//        Dashboard dashboard = new Dashboard();
//        List<Map<String, String>>  taskType = userService.findUserById(userTask).getTaskCategory();
//
//        Arrays.stream(TaskType.values()).forEach(taskType1 -> {
//            Map<String, String> type = new HashMap<>();
//            type.put("typeName",taskType1.toString());
//            taskType.add(type);
//        });
//
//        for (Map<String, String> s : taskType) {
////            List<ToDoItem> items = new ArrayList<>();
//            int itemSize = 0;
//            HashMap<String, String> value = new HashMap<>();
//            String projName =s.get("typeName");
//            int  completedTaskInProj = 0;
//            for (ToDoItem toDoItem : toDoItems) {
//                if (toDoItem.getTaskType().equals(s)) {
//                    itemSize++;
////                    items.add(toDoItem);
//                    if (toDoItem.isCompleted()){
//                        completedTaskInProj+=1;
//                    }
//                }
//            }
//            int completedTask;
//            try {
//                completedTask = (completedTaskInProj /itemSize)*100;
//            }catch (ArithmeticException a){
//                completedTask = 0;
//            }
//
//            value.put("taskSizeInProject", String.valueOf(itemSize));
//            value.put("totalCompletedTask", String.valueOf(completedTask));
//            value.put("projectName", projName.replace('_',' '));
//
//
//            lists.add(value);
//        }
//
//        List<ToDoItem> todayItem = getTodayTask(userTask).getItems();
//        dashboard.setTodayItem(todayItem);
//        int totalSize = todayItem.size();
//        int[] completed = {1};
//        todayItem.forEach(x->{
//            if (x.isCompleted()){
//                completed[0]+=1;
//            }
//        });
//        dashboard.setGroupedTask(lists);
//        try {
//            int total = (completed[0] / totalSize) * 100;
//            dashboard.setTotalCompleted(total);
//        }catch (RuntimeException e){
//            dashboard.setTotalCompleted(0);
//        }
//
////        dashboard.setItemsGroups(Arrays.stream(TaskType.values()).map(x->String.valueOf(x).replace('_', ' ')).toList());

        return dashboard;
    }

    public List<ToDoItem> getInProgressTask(int id) throws Exception {
        User user = userService.findUserById(id);
        List<ToDoItem> lists = new ArrayList<>();
        List<ToDoItem> toDoItems = user.getMyTask();

        for (ToDoItem toDoItem : toDoItems) {
            if (checkIfItInProgress(toDoItem)){
                lists.add(toDoItem);
                toDoItem.setTaskStatus(TaskStatus.INPROGRESS);
                toDoItems.set(toDoItems.indexOf(toDoItem), toDoItem);
            }
        }
        user.setMyTask(toDoItems);
        userRepo.save(user);

        return lists;
    }

    public static boolean checkIfItCompleted(ToDoItem toDoItem){
        LocalDateTime now = LocalDateTime.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime fullDateTime = toDoItem.getStartDate();
        LocalDate startDate = fullDateTime.toLocalDate();
        LocalTime startTime = fullDateTime.toLocalTime();

        LocalDate endDate = toDoItem.getDueDate().toLocalDate(); // Example assumption
        LocalTime endTime = toDoItem.getDueDate().toLocalTime(); // Example assumption

        if (startDate.isEqual(endDate)) {
            // Check if the start time equals the end time
            if (startTime == endTime) {
                // Check if the start time, end time, and current time (now) are equal
                if (startTime.equals(nowTime)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkIfItInProgress(ToDoItem toDoItem){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fullDateTime = toDoItem.getStartDate();
        LocalDate startDate = fullDateTime.toLocalDate();
        LocalTime startTime = fullDateTime.toLocalTime();

        LocalDate endDate = toDoItem.getDueDate().toLocalDate(); // Example assumption
        LocalTime endTime = toDoItem.getDueDate().toLocalTime(); // Example assumption

        if (startDate.isBefore(now.toLocalDate()) || startDate.isEqual(now.toLocalDate())) {
            // Check if the start time is now or has passed
            if (startTime.isBefore(now.toLocalTime()) || startTime.equals(now.toLocalTime())) {
                // Check if the start time is not equal to or has not passed the end time
                if (startTime.isBefore(endTime)) {
                    // Check if the start date is not equal to or has not passed the end date
                    if (startDate.isBefore(endDate)) {
                        return true; // All conditions met
                    }
                }
            }
        }
        return false;
    }


    @Override
    public AppPackage getAppPackage(int userTask) throws Exception {
        FindTaskPackage findTaskPackage = new FindTaskPackage();
        AppPackage appPackage = new AppPackage();
        appPackage.setDashboard(getDashboardPackage(userTask));
        findTaskPackage.setAllTask(getAllTasks(userTask));
        appPackage.setFindTaskPackage(findTaskPackage);

        return appPackage;
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
            LocalDate now = LocalDate.now();
            LocalDateTime fullDateTime = item.getStartDate();
            LocalDate startDate = fullDateTime.toLocalDate();
            LocalDate endDate = item.getDueDate().toLocalDate();

            if (startDate.isBefore(now) || startDate.isEqual(now)
                    && startDate.isBefore(endDate)) {
                todayTask.add(item);
            }
        }
        return new FindTasksResponse(todayTask);
    }

    public FindTasksResponse findByDate(String date, int userId) throws Exception {
        LocalDateTime dateFormat = getDate(date);
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
//        HashMap<String, String>  getUpdate = updateAllRequest.getRequestMap();
//        ToDoItem task = taskBy_StartDate_EndDate_TaskType(
//                updateAllRequest.getUserId(),
//                getDate(getUpdate.get("startDate")),
//                getDate(getUpdate.get("dueDate")),
//                verifyEnum(getUpdate.get("taskType"), userService.findUserById(updateAllRequest.getUserId())));
//        if (task!=null) {
//            if (!task.isCompleted()) {
//                ToDoItem item = updateAllMap.updateFromMap(updateAllRequest.getRequestMap(), task);
//                todoItemRepo.save(item);
//            }
//        }
    }

    @Override
    public MobileNavPackage getMobileNavPackage(int id) throws Exception {
        MobileNavPackage mobileNavPackage = new MobileNavPackage();
        mobileNavPackage.setDashboard(getDashboardPackage(id));
        mobileNavPackage.setTodayTask(getTodayTaskPackage(id));
        return mobileNavPackage;
    }

    private TodayTask getTodayTaskPackage(int id) {
        return null;
    }

    ToDoItem taskBy_StartDate_EndDate_TaskType(int userId,
                                                         LocalDateTime s,
                                                         LocalDateTime e,
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


