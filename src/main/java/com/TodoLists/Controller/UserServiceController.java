package com.TodoLists.Controller;

//import com.TodoLists.Application.DTOs.Request.*;

import ch.qos.logback.core.encoder.EchoEncoder;
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
    public ResponseEntity<LoginResponse> createAccount(@RequestBody CreateUserRequest createUserRequest){
        LoginResponse lr =  new LoginResponse();
        try{
            lr = userService.createAccount(createUserRequest);
            return ResponseEntity.ok().body(lr);
        } catch (Exception e) {
            lr.setMessage(e.getMessage());
            lr.setId(-1);
            return  ResponseEntity.badRequest().body(lr);
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
            return  ResponseEntity.status(200).body(loginResponse);
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

    @GetMapping("/getMobileNavPackage/{id}")
    public ResponseEntity<MobileNavPackage> getMobileNavPackage(@PathVariable("id") int id){
        try {
            return ResponseEntity.ok().body(todoItemService.getMobileNavPackage(id));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getAppPackage/{id}")
    public AppPackage getAppPackage(@PathVariable("id") int id){
        try {
            System.out.println("id i gott is "+id);
           return todoItemService.getAppPackage(id);
        }catch (Exception e){
            e.fillInStackTrace();
            e.getCause();
            e.printStackTrace();
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

    @PostMapping("/deleteAll")
    public String deleteAll(){
        try{
            userService.deleteAll();
            return "delete successful";
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

//
//    @GetMapping("/todayTasks/{id}")
//    public ResponseEntity<FindTasksResponse> getTodayTask(@PathVariable("id") int id){
//        try {
//            return ResponseEntity.ok().body(todoItemService.getTodayTask(id));
//        }catch (Exception e){
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//    @GetMapping("/findTask")
//    public void findTask(){
//
//    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<String> logout(@PathVariable("id") int id){
        try{
            userService.logOut(id);
             return ResponseEntity.ok().body("logged out");
        } catch (Exception e) {
            return  ResponseEntity.ok().body("Error: "+e.getMessage());
        }
    }

    @PostMapping("/addTask")
    public String addTask(@RequestBody AddTaskRequest addTaskRequest){
        try{//TODO check ur mf get dashboard it not getting typename etc color
            //TODO check the get user and make it update the tasktype or just do it in the add task
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


    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
        try{
            String response = userService.resetPassword(forgetPasswordRequest);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.fillInStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
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
    public ResponseEntity<String> deleteTask(int userId, int todoItemIds){
        try {
            String response = todoItemService.deleteTask(userId,todoItemIds);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
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


@PostMapping("/markComplete/{userId}/{todoItemIds}")
    public ResponseEntity<MobileNavPackage> markItemASComplete(@PathVariable("todoItemIds") int todoItemIds,
                                     @PathVariable("userId") int userId){
    try {
        MobileNavPackage mobilePackage = todoItemService.markItemASComplete(userId,todoItemIds);
        return ResponseEntity.ok().body(mobilePackage);
    }catch (Exception e){
        throw new RuntimeException(e.getMessage());
    }
    }

//    @GetMapping("/getTaskByDate/{date}/{userId}")
//    public ResponseEntity<FindTasksResponse> findTasksByDate
//            (@PathVariable("date") String date, @PathVariable("userId") int userId){
//        try{
//           return ResponseEntity.ok().body(todoItemService.findByDate(date, userId));
//        } catch (Exception e) {
//            return null;
//        }
//    }

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
