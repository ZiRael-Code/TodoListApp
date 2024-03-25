package com.TodoLists.Application.Controller;

import com.TodoLists.Application.DTOs.Request.AddTaskRequest;
import com.TodoLists.Application.DTOs.Request.CreateUserRequest;
import com.TodoLists.Application.DTOs.Request.LoginRequest;
import com.TodoLists.Application.DTOs.Response.LoginResponse;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.DTOs.Request.UpdateTask;
import com.TodoLists.Application.Services.TodoItemServiceRepo;
import com.TodoLists.Application.Services.UserServiceImpl;
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
    private TodoItemServiceRepo todoItemServiceRepo;
    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody CreateUserRequest createUserRequest){
        try{
            userService.createAccount(createUserRequest);
            return ResponseEntity.ok().body("{\"message\": \"Account Created\"}");
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
            return  ResponseEntity.badRequest().body(loginResponse);
        }
    }

    @GetMapping("/findUser")
    public void findUser(){

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
            todoItemServiceRepo.addTask(addTaskRequest);

            return "task added successful";
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
            todoItemServiceRepo.updateTitle(updateTask);
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

    @PostMapping("/markItemASComplete")
    public String markItemASComplete(int userId, int todoItemIds, boolean mark) throws Exception{
        try {
            todoItemServiceRepo.markItemASComplete(userId,todoItemIds,mark);
            return "Completed Updated";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/deleteTask")
    public String deleteTask(int userId, int todoItemIds){
        try {
            todoItemServiceRepo.deleteTask(userId,todoItemIds);
            return "Task Deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAllTasks")
    public List<ToDoItem> getAllTasks(int userId){
        try {
            return todoItemServiceRepo.getAllTasks(userId);
        }catch (Exception e){
            return null;
        }
}

}
