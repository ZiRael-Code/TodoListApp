package com.TodoLists.Application.Controller;

import com.TodoLists.Application.DTOs.AddTaskRequest;
import com.TodoLists.Application.DTOs.CreateUserRequest;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.Services.UpdateTask;
import com.TodoLists.Application.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserServiceController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/createAccount")
    public String createAccount(@RequestBody CreateUserRequest createUserRequest){
        try{
            userService.createAccount(createUserRequest);
            return "Account Created";
        } catch (Exception e) {
            return "Error: "+e.getMessage();
        }
    }

    @GetMapping("/findUser")
    public void findUser(){

    }

    @GetMapping("/findTask")
    public void findTask(){

    }

    @PostMapping("/login")
    public int login(@RequestBody CreateUserRequest createUserRequest){
        try{
            return userService.logIn(createUserRequest);

        } catch (Exception e) {
            return 0;
        }
    }

    @PostMapping("/login/{username}")
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
            userService.addTask(addTaskRequest);

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
            userService.updateTitle(updateTask);
            return "Title Updated";
        }catch (Exception e){
           return e.getMessage();
        }
    }

    @PostMapping("/updateDescription")
    public String updateDescription(UpdateTask updateTask) throws Exception {
        try {
            userService.updateDescription(updateTask);
            return "Description Updated";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @PostMapping("/markItemASComplete")
    public String markItemASComplete(int userId, int todoItemIds, boolean mark) throws Exception{
        try {
            userService.markItemASComplete(userId,todoItemIds,mark);
            return "Completed Updated";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/deleteTask")
    public String deleteTask(int userId, int todoItemIds) throws Exception{
        try {
            userService.deleteTask(userId,todoItemIds);
            return "Task Deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAllTasks")
    public List<ToDoItem> getAllTasks(int userId){
        try {
            return userService.getAllTasks(userId);

        }catch (Exception e){
            return null;
        }
}

}
