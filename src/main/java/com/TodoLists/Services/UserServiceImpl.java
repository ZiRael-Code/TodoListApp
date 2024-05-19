package com.TodoLists.Services;

import com.TodoLists.DTOs.Response.LoginResponse;
import com.TodoLists.Data.Model.User;
import com.TodoLists.DTOs.Request.CreateUserRequest;
import com.TodoLists.DTOs.Request.LoginRequest;
import com.TodoLists.DTOs.Response.MyNotification;
import com.TodoLists.Data.Model.Notification;
import com.TodoLists.Data.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserServiceRepo {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    NotificationServie notificationServie;

    void validate(CreateUserRequest createUserRequest){
        if (createUserRequest.getUsername() == null && createUserRequest.getPassword() == null){
            throw new RuntimeException("Please input the require fields Username, Password");
        }
    }

    @Override
    public LoginResponse createAccount(CreateUserRequest createUserRequest) throws Exception {
        validate(createUserRequest);
        User user1 = new User();
        user1.setEmail(createUserRequest.getEmail());
        user1.setPassword(createUserRequest.getPassword());
        user1.setUsername(createUserRequest.getUsername());
        List<Notification> notifications = user1.getMyNotification();
        notifications.add(notificationServie.newUser(createUserRequest.getUsername()));
        user1.setMyNotification(notifications);
        User user2 = userRepo.save(user1);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user2.getId());
        return loginResponse;
    }

    @Override
    public LoginResponse logIn(LoginRequest loinRequest) throws Exception {
//        User user1 = findByUsername(loinRequest.getUsername());
        User user1 = userRepo.findByUsernameAndPassword(loinRequest.getUsername(), loinRequest.getPassword());
        if (user1.equals(userRepo.findByUsernameAndPassword(loinRequest.getUsername(), loinRequest.getPassword()))) {
            user1.setEnable(true);
            List<Notification> notifications = user1.getMyNotification();
            notifications.add(notificationServie.newUser(loinRequest.getUsername()));
            user1.setMyNotification(notifications);
            user1 = userRepo.save(user1);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(user1.getId());
            loginResponse.setMessage("Login Successful");
            loginResponse.setLoginStatus(user1.isEnable());
            loginResponse.setUsername(user1.getUsername());
            return loginResponse;


        }else {
            throw new RuntimeException("User name or password not correct");
        }
    }

    public User findByUsername(String username) throws Exception {
        User user = userRepo.findByUsername(username);
        if (user!=null){
            List<Notification> notifications = user.getMyNotification();

           List<Notification> dueDate = notificationServie.closeToDueDate(user.getMyTask());
            notifications.add(dueDate.stream().iterator().next());

            List<Notification> startDate = notificationServie.closeToStartDate(user.getMyTask());
            notifications.add(startDate.stream().iterator().next());
            user.setMyNotification(notifications);
            return user;
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public void logOut(String username) throws Exception {
        User user1 = findByUsername(username);
        user1.setEnable(false);
        userRepo.save(user1);
    }
    @Override
    public User findUserById(int userId) throws Exception {
        User user = userRepo.findById(userId);
        List<Notification> notifications = user.getMyNotification();

        List<Notification> close = notificationServie.closeToDueDate(user.getMyTask());
        List<Notification> start = notificationServie.closeToStartDate(user.getMyTask());
       if (close != null || start != null) {
           notifications.add(close.stream().iterator().next());
           user.setMyNotification(notifications);

           notifications.add(start.stream().iterator().next());
           user.setMyNotification(notifications);
            userRepo.save(user);
       }

        return user;
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
}
