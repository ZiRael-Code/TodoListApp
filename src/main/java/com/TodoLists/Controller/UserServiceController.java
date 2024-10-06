package com.TodoLists.Controller;

//import com.TodoLists.Application.DTOs.Request.*;

import com.TodoLists.DTOs.Request.*;
import com.TodoLists.DTOs.Response.*;
import com.TodoLists.Data.Model.ToDoItem;
import com.TodoLists.Data.Model.User;
import com.TodoLists.Services.TodoItemService;
import com.TodoLists.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserServiceController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TodoItemService todoItemService;
    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody CreateUserRequest createUserRequest){
        try{
            userService.createAccount(createUserRequest);
            return ResponseEntity.ok().body("Account Created");
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest createUserRequest){
        try{
            LoginResponse loginResponse = userService.logIn(createUserRequest);
            return ResponseEntity.ok().body(loginResponse);
        } catch (Exception e) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMessage(e.getMessage());
            loginResponse.setId(-1);
            return  ResponseEntity.badRequest().body(loginResponse);
        }
    }

    @GetMapping("/getProjectTypeGroup")
    public ResponseEntity<List<ToDoItem>> getProjectTypeGroup(@RequestBody GetProjectGroupReq getProjectGroupReq) throws Exception {
        try {
            return ResponseEntity.ok().body(todoItemService.findTaskGroup(getProjectGroupReq));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getDashboard/{id}")
    public Dashboard getDashboardPackage(@PathVariable("id") int id){
        try {
           return todoItemService.getDashboardPackage(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @GetMapping("/getAppPackage/{id}")
    public AppPackage getAppPackage(@PathVariable("id") int id){
        try {
           return todoItemService.getAppPackage(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage()+"\n"+e.fillInStackTrace());
        }
    }

    @GetMapping("/findUser")
    public void findUser(){
    }
    @GetMapping("/getMyNotification/{id}")
    public ResponseEntity<MyNotification> getMyNotification(@PathVariable("id") int id){
    try {
        return ResponseEntity.ok().body(userService.myNotifications(id));
    }catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }
    }
    @GetMapping("/todayTasks/{id}")
    public ResponseEntity<FindTasksResponse> getTodayTask(@PathVariable("id") int id){
        try {
            return ResponseEntity.ok().body(todoItemService.getTodayTask(id));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @GetMapping("/findTask")
    public void findTask(){

    }

    @PostMapping("/logout/{username}")
    public String logout(@PathVariable("username") String username){
        try{
            userService.logOut(username);
            return "Login Successful";
        } catch (Exception e) {
            return "Error: "+e.getMessage();
        }
    }

    @PostMapping("/addTask")
    public String addTask(@RequestBody AddTaskRequest addTaskRequest){
        try{
            todoItemService.addTask(addTaskRequest);
            return "{\"task added successful\"}";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/deleteAccount")
    public String delete(@RequestBody CreateUserRequest createUserRequest){
        try{
            userService.deleteAccount(createUserRequest);
            return "Delete successful";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @PostMapping("/updateTitle")
   public String updateTitle(UpdateTask updateTask) throws Exception{
        try {
            todoItemService.updateTitle(updateTask);
            return "Title Updated";
        }catch (Exception e){
           return e.getMessage();
        }
    }

    @PostMapping("/updateDescription")
    public String updateDescription(UpdateTask updateTask) throws Exception {
        try {
//            userService.updateDescription(updateTask);
            return "Description Updated";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

//    @PostMapping("/markItemASComplete")
//    public String markItemASComplete(int userId, int todoItemIds, boolean mark) throws Exception{
//        try {
//            todoItemServiceRepo.markItemASComplete(userId,todoItemIds);
//            return "Completed Updated";
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }

    @PostMapping("/deleteTask")
    public String deleteTask(int userId, int todoItemIds){
        try {
            todoItemService.deleteTask(userId,todoItemIds);
            return "Task Deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAllTasks/{userId}")
    public List<ToDoItem> getAllTasks(@PathVariable("userId") int userId){
        try {
            return todoItemService.getAllTasks(userId);
        }catch (Exception e){
            return null;
        }
}

@PostMapping("/complete/{userId}/{todoItemIds}")
    public String markItemASComplete(@PathVariable("userId") int userId,
                                     @PathVariable("todoItemIds") int todoItemIds){
    try {
        todoItemService.markItemASComplete(userId,todoItemIds);
        return "Completed Updated";
    }catch (Exception e){
        return e.getMessage();
    }
    }

    @GetMapping("/getTaskByDate/{date}/{userId}")
    public ResponseEntity<FindTasksResponse> findTasksByDate
            (@PathVariable("date") String date, @PathVariable("userId") int userId){
        try{
           return ResponseEntity.ok().body(todoItemService.findByDate(date, userId));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getUser/{userId}")
    public User getUser(@PathVariable("userId") int userId){
        try{
            return (userService.findUserById(userId));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    @GetMapping("/getDashboard/{userId}")
//    public DashBoardResponse mobileDashboardMobile(@PathVariable("userId") int userId){
//        try{
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//   }

}
