package com.TodoLists.Services;

import com.TodoLists.DTOs.Response.AppPackage;
import com.TodoLists.DTOs.Response.FindTasksResponse;
import com.TodoLists.DTOs.Response.MobileNavPackage;
import com.TodoLists.Data.Repository.TodoItemRepo;
import com.TodoLists.DTOs.Request.*;
import com.TodoLists.Data.Model.*;
import com.TodoLists.Data.Repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    public List<ToDoItem> getAllTasks(int userId) throws Exception {
        List<ToDoItem> allTask = todoItemRepo.findAllUserTask(userId);
        return allTask;
    }




    @Override
    public MobileNavPackage markItemASComplete(int userId, int todoItemIds) throws Exception {
        ToDoItem item = todoItemRepo.findTask(todoItemIds, userId);
        TaskStatus taskStatus = getTaskStatus(item.getStartDate(), item.   getDueDate());
        item.setTaskStatus(taskStatus);
        System.out.println("&&^^%%:= the task status i agreed o is"+taskStatus);
//            notificationServie.inProgress(item.getTaskName());

        todoItemRepo.save(item);
        return getMobileNavPackage(userId);
    }

    public TaskStatus getTaskStatus(LocalDateTime startDate, LocalDateTime dueDate) {
        LocalDateTime now = LocalDateTime.now();

        if (dueDate.isBefore(now) || dueDate.isEqual(now)) {
            return TaskStatus.COMPLETED;
        }else if (startDate.toLocalDate().isBefore(now.toLocalDate())){
            return TaskStatus.TODO;
        }

        return TaskStatus.INPROGRESS;
    }



    public void addTask(AddTaskRequest addReq) throws Exception {
        User user11 = userService.findUserById(addReq.getUserId());
        System.out.println(user11.getId() +" Is the id user  found");
        if (user11.isEnable()) {
            Map<String, String> tasktype;
            List<Map<String, String>> types = user11.getTaskCategory();
            if ((verifyEnum(addReq.getTaskType(),
                    userService.findUserById(addReq.getUserId())))){
               tasktype = (addReq.getTaskType());
            }else {
                tasktype = (addReq.getTaskType());
                types.add(addReq.getTaskType());
            }
            LocalDateTime dueDate = getDate(addReq.getEndDate());
            LocalDateTime startDate = getDate(addReq.getStartDate());
            Priority priority = Priority.valueOf(addReq.getPriority());
            String description = addReq.getDescription();
            int uId = addReq.getUserId();
            String title = addReq.getTitle();

            ToDoItem newTask = mapInfoToNewTask(title, description, priority, startDate, dueDate, uId, tasktype);

             Map<String, List<Notification>> notifications = notificationServie.newTask(user11, newTask);
            user11.setMyNotification(notifications);


           ToDoItem item = todoItemRepo.save(newTask);
           List<ToDoItem> task = user11.getMyTask();
           task.add(item);
           user11.setMyTask(task);
           user11.setTaskCategory(types); // keep an eye on this
            userRepo.save(user11);

        } else {
            throw new Exception("User not logged in ");
        }
    }

    private static ToDoItem mapInfoToNewTask(String title, String description, Priority priority, LocalDateTime startDate, LocalDateTime dueDate, int uId, Map<String, String> tasktype) {
        ToDoItem newTask = new ToDoItem();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setPriority(priority);
        newTask.setStartDate(startDate);
        newTask.setDueDate(dueDate);
        newTask.setUserId(uId);
        newTask.setTaskStatus(TaskStatus.TODO);
        newTask.setTaskType(tasktype);
        return newTask;
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

    public static LocalDateTime getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(date, formatter);
    }


    public ToDoItem getTask(int taskId, int userId) throws Exception {
        List<User> allUser =  userRepo.findAll();
        int uId = allUser.get(allUser.size()-1).getId();
        if (userId > uId || userId < 1) {
            throw new RuntimeException("User does not exist");
        }else {
            List<ToDoItem> all = todoItemRepo.findAllUserTask(userId);
            int tId = all.get(all.size()-1).getTodoItemId();
            if (taskId > tId || taskId < 1){
                throw new RuntimeException("Task does not exist");
            }else {
                return todoItemRepo.findTask(taskId, userId);
            }
        }
    }

    @Override
    public String deleteTask(int userId, int todoItemId) throws Exception{
        ToDoItem task = getTask(todoItemId, userId);
        User user = userService.findUserById(userId);
        List<ToDoItem> allUserTask = user.getMyTask();
        allUserTask.remove(task);
        user.setMyTask(allUserTask);
        userRepo.save(user);
        return "Task deleted successfully";
    }

    public Dashboard getDashboardPackage(int userTask) throws Exception {
        Dashboard dashboard = new Dashboard();
        System.out.println("in get dashboard package");
        User user = userService.findUserById(userTask);
        List<Map<String, String>> groupedTasks = new ArrayList<Map<String, String>>();
        int completedTask = 0;
        String message = "";
        for (Map<String, String> taskType : user.getTaskCategory()){
            Map<String, String> taskGroup = new HashMap<String, String>();
            Map<String, String> taskTypeMap = taskType;
            int itemSize = 0;
            int completedTaskInProjectName = 0;
            List<ToDoItem> projectTask = new ArrayList<>();

            for (ToDoItem toDoItem : user.getMyTask()) {
                if (toDoItem.getTaskType().get("typeName").equalsIgnoreCase(taskType.get("typeName"))) {
                    projectTask.add(toDoItem);
                    itemSize++;
                }
                if (toDoItem.getTaskType().get("typeName").equalsIgnoreCase(taskType.get("typeName")) &&
                        toDoItem.getTaskStatus().equals(TaskStatus.COMPLETED)) {
                    completedTaskInProjectName++;
                }
                if (toDoItem.getTaskStatus().equals(TaskStatus.COMPLETED) ){
                    completedTask+=1;
                }
            }

                taskGroup.put("typeName", taskTypeMap.get("typeName"));
                taskGroup.put("color", taskTypeMap.get("color"));
                taskGroup.put("icon", taskTypeMap.get("icon"));
            taskGroup.put("taskSizeInProject", String.valueOf(itemSize));
            taskGroup.put("completedTask", String.valueOf(completedTaskInProjectName));


            ObjectMapper objectMapper = new ObjectMapper();
            String projectTaskJson = objectMapper.writeValueAsString(projectTask);

            taskGroup.put("projectTask", projectTaskJson);
            groupedTasks.add(taskGroup);
          }
        if (completedTask == 0) {
            message = "You don't have any tasks today 😎";
        }else {
            message = "You have some pending task today";
        }
        Map<String, String> completed = new HashMap<String, String>();
        completed.put("completedTask", String.valueOf(completedTask));
        completed.put("message", message);


        dashboard.setTotalCompleted(completed);
        dashboard.setGroupedTask(groupedTasks);
        dashboard.setTaskGroupSize(groupedTasks.size());
        dashboard.setAllTasks(user.getMyTask());
        dashboard.setUser(user);

        return dashboard;
    }

    public com.TodoLists.DTOs.Request.TodayTask getProjectGroupTask(int uId) throws Exception {
        com.TodoLists.DTOs.Request.TodayTask todayTask;
        User user = userService.findUserById(uId);
        List<ToDoItem> allTasks = user.getMyTask();
        List<ToDoItem> todoTask = new ArrayList<>();
        List<ToDoItem> inprogressTask = new ArrayList<>();
        List<ToDoItem> completedTask = new ArrayList<>();

        for (ToDoItem task: allTasks) {
            if (task.getTaskStatus().equals(TaskStatus.TODO)){
                todoTask.add(task);
            } else if (task.getTaskStatus().equals(TaskStatus.INPROGRESS)) {
                inprogressTask.add(task);
            } else if (task.getTaskStatus().equals(TaskStatus.COMPLETED)) {
                completedTask.add(task);
            }
        }
        todayTask = new com.TodoLists.DTOs.Request.TodayTask(allTasks, todoTask, inprogressTask, completedTask);
        return todayTask;
    }

    public User getUserNotification(int uId) throws Exception {
        User user = userService.findUserById(uId);
        return user;
    }

    @Override
    public AppPackage getAppPackage(int userTask) throws Exception {
        FindTaskPackage findTaskPackage = new FindTaskPackage();
        AppPackage appPackage = new AppPackage();
        System.out.println("before all ");
        appPackage.setDashboard(getDashboardPackage(userTask));
        System.out.println("after setting the dashboard ");
        findTaskPackage.setAllTask(getAllTasks(userTask));
        System.out.println("after setting the All task ");
        appPackage.setFindTaskPackage(findTaskPackage);
        System.out.println("after setting the find task package  ");

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


    //user this method for the DASHBOARD ANALYTICS, *U DONT HAVE ANY PENDING TASK TODAY
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

    @Override
    public void updateAll(UpdateAllRequest updateAllRequest) throws Exception {
        User user = userService.findUserById(updateAllRequest.getUserId());
        ToDoItem task = getTask(user.getId(), updateAllRequest.getId());
        HashMap<String, String> toUpdateInfo = updateAllRequest.getRequestMap();
        LocalDateTime dueDate = getDate(toUpdateInfo.get("dueDate"));
        LocalDateTime startDate = getDate(toUpdateInfo.get("startDate"));
        Priority priority = Priority.valueOf(toUpdateInfo.get("priority"));
        ToDoItem newTask = mapInfoToNewTask(toUpdateInfo.get("title"), toUpdateInfo.get("description"),
                priority, startDate, dueDate,
                updateAllRequest.getUserId(), task.getTaskType()
        );
        newTask.setTodoItemId(task.getTodoItemId());
    }

    @Override
    public MobileNavPackage getMobileNavPackage(int id) throws Exception {
        MobileNavPackage mobileNavPackage = new MobileNavPackage();
        mobileNavPackage.setDashboard(getDashboardPackage(id));
        mobileNavPackage.setTodayTask(getProjectGroupTask(id));
        mobileNavPackage.setUser(getUserNotification(id));

        return mobileNavPackage;
    }




}


