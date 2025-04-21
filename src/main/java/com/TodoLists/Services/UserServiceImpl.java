package com.TodoLists.Services;

import com.TodoLists.DTOs.Request.ForgetPasswordRequest;
import com.TodoLists.DTOs.Response.LoginResponse;
import com.TodoLists.Data.Model.*;
import com.TodoLists.DTOs.Request.CreateUserRequest;
import com.TodoLists.DTOs.Request.LoginRequest;
import com.TodoLists.DTOs.Response.MyNotification;
import com.TodoLists.Data.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    NotificationServie notificationServie;

    void validate(CreateUserRequest createUserRequest){
        if (createUserRequest.getUsername() == null && createUserRequest.getPassword() == null){
            throw new RuntimeException("Please input the require fields Username, Password");
        }else if (createUserRequest.getUsername().length() < 5){
            throw new RuntimeException("Username cannot be lesser than 5");
        }else if (!createUserRequest.getUsername().matches("^[a-zA-Z0-9]+$")){
            throw new RuntimeException("Username must contain letter and numbers");
        }else if (createUserRequest.getPassword().length() < 8){
            throw new RuntimeException("Password must be at least eight characters");
        }else if (!createUserRequest.getEmail().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")){
            throw new RuntimeException("Please enter a valid Email Address");
        }
    }

    @Override
    public LoginResponse createAccount(CreateUserRequest createUserRequest) throws Exception {
        try {
            findByUsername(createUserRequest.getUsername());
            System.out.println("$%^@ user found ");
            throw new Exception("Username taken, make sure preferred username contains letters and number and is more than 5");
        }catch (Exception e){
            System.out.println("catch message is "+e.getMessage());
        validate(createUserRequest);
        User user1 = new User();
        user1.setEmail(createUserRequest.getEmail());
        user1.setPassword(createUserRequest.getPassword());
        user1.setUsername(createUserRequest.getUsername());
        Map<String, List<Notification>> notifications = notificationServie.newUser(user1);
        user1.setMyNotification(notifications);
        List<Map<String , String>> category = user1.getTaskCategory();
        Arrays.stream(TaskType.values()).forEach(taskType1 -> {
            Map<String, String> type = new HashMap<>();
            type.put("typeName",taskType1.toString());
            type.put("color",null);
            type.put("icon",null);
            category.add(type);
        });
        user1.setTaskCategory(category);
        User user2 = userRepo.save(user1);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user2.getId());
        loginResponse.setEmail(user2.getEmail());
        return loginResponse;
    }
    }

    @Override
    public LoginResponse logIn(LoginRequest loinRequest) throws Exception {
//        User user1 = findByUsername(loinRequest.getUsername());
        User user1 = userRepo.findByUsernameAndPassword(loinRequest.getUsername(), loinRequest.getPassword());
        if (user1.equals(userRepo.findByUsernameAndPassword(loinRequest.getUsername(), loinRequest.getPassword()))) {
            user1.setEnable(true);
            Map<String, List<Notification>> notifications = (notificationServie.newUser(user1));
            user1.setMyNotification(notifications);
            user1 = userRepo.save(user1);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(user1.getId());
            loginResponse.setMessage("Login Successful");
            loginResponse.setLoginStatus(user1.isEnable());
            loginResponse.setUsername(user1.getUsername());
            loginResponse.setEmail(user1.getEmail());
            return loginResponse;
        }else {
            throw new RuntimeException("User name or password not correct");
        }
    }

    public User findByUsername(String username) throws Exception {
        User user = userRepo.findByUsername(username);
        if (user!=null){
            return findUserById(user.getId());
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public void logOut(int id) throws Exception {
        User user1 = findUserById(id);
        user1.setEnable(false);
        userRepo.save(user1);
    }
    @Override
    public User findUserById(int userId) throws Exception {
        User user = userRepo.findById(userId);
        System.out.println(user.getId() +" is the id user found");

        Map<String, List<Notification>> closeToStartDate = notificationServie.closeToStartDate(user);
        user.setMyNotification(closeToStartDate);
        Map<String, List<Notification>> closeToDueDate = notificationServie.closeToDueDate(user);
        user.setMyNotification(closeToDueDate);
        return userRepo.save(user);
    }

    @Override
    public void deleteAccount(CreateUserRequest createUserRequest) throws Exception {
        validate(createUserRequest);
        User user1 = new User();
        user1.setPassword(createUserRequest.getPassword());
        user1.setUsername(createUserRequest.getUsername());
       userRepo.deleteAccountByUsernameAndPassword(user1);
    }

    public List<User> findAllUser() throws Exception {
        return userRepo.findAll();
    }


    @Override
    public MyNotification myNotifications(int id) throws Exception {
        User user = findUserById(id);
        return new MyNotification(user.getMyNotification());
    }

    @Override
    public String resetPassword(ForgetPasswordRequest forgetPasswordRequest) throws Exception {
        User user = findByUsername(forgetPasswordRequest.getUsername());
            if (user.getPassword().equals(forgetPasswordRequest.getOldPassword())){
                user.setPassword(forgetPasswordRequest.getNewPassword());
                userRepo.save(user);
                return "Password reset successfully";
            }else {
                throw new RuntimeException("Your password does not match the old one");
            }
    }



    public static void main(String[] args) {
        
    }
}
