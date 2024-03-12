package com.TodoLists.Application.Services;

import com.TodoLists.Application.DTOs.AddTaskRequest;
import com.TodoLists.Application.DTOs.CreateUserRequest;
import com.TodoLists.Application.Data.Model.ToDoItem;
import com.TodoLists.Application.Data.Model.User;
import com.TodoLists.Application.Data.Repository.UserRepoImpl;
import com.TodoLists.Application.Data.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
// TODO let ur repos generate the ids
public class UserServiceImpl implements UserServiceRepo {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private UserRepoImpl user;
    int taskId=  1;

    public ToDoItem findAllUserTask(int userId) throws Exception {
        List<ToDoItem> todo = userRepo.findById(userId).getMyTask();
        for (ToDoItem toDoItem: todo) {
            if (toDoItem.getUserId() == userId){
                return toDoItem;
            }
        }
        throw new Exception("User not Exist");
    }

    public ToDoItem findTask(int taskId, int userId) throws Exception {
        User user11 =  userRepo.findById(userId);
        if (user11.isEnable()) {
            List<ToDoItem> item = userRepo.findById(userId).getMyTask();
            for (ToDoItem item1: item) {
                if (item1.getId()==taskId){
                    return item1;
                }
            }

        }
        return null;
    }


    void validate(CreateUserRequest createUserRequest){
        if (createUserRequest.getUsername() == null && createUserRequest.getPassword() == null){
            throw new RuntimeException("Please input the require fields Username, Password");
        }
    }

    @Override
    public void createAccount(CreateUserRequest createUserRequest) throws Exception {
        validate(createUserRequest);
        User user1 = new User();
        user1.setPassword(createUserRequest.getPassword());
        user1.setUsername(createUserRequest.getUsername());
        userRepo.save(user1);
    }

    @Override
    public int logIn(CreateUserRequest createUserRequest) throws Exception {
        User user1 = user.findByUsername(createUserRequest.getUsername());
        user1.setEnable(true);
        user1.setCurrentId(user1.getId());
        userRepo.save(user1);
        return user1.getId();
    }

    @Override
    public void logOut(String username) throws Exception {
        User user1 = user.findByUsername(username);
        user1.setEnable(false);
        userRepo.save(user1);
    }

    @Override
    public void deleteAccount(CreateUserRequest createUserRequest) throws Exception {
        validate(createUserRequest);
        User user1 = new User();
        user1.setPassword(createUserRequest.getPassword());
        user1.setUsername(createUserRequest.getUsername());
       userRepo.deleteAccountByUsernameAndPassword(user1);
    }


    public void addTask(AddTaskRequest addReq) throws Exception {
        User user11 =  userRepo.findById(addReq.getUserId());
        List<ToDoItem> list = user11.getMyTask();
//            if (user11.isEnable()){
                ToDoItem newTask = new ToDoItem();
                newTask.setTitle(addReq.getTitle());
                newTask.setDescription(addReq.getDescription());
                newTask.setPriority(addReq.getPriority());
                newTask.setDueDate(addReq.getDueDate());
                newTask.setUserId(user11.getId());
                list.add(newTask);
                user11.setMyTask(list);
                userRepo.save(user11);

//            }throw new Exception("User not logged in ");
        }

    public List<ToDoItem> getAllTasks(int userId) throws Exception {
        return userRepo.findById(userId).getMyTask();
    }


    public List<User> findAllUser() throws Exception {
        return userRepo.findAll();
    }

    void save(ToDoItem task) throws Exception {
     User user1 = userRepo.findById(task.getUserId());
        List<ToDoItem> items = user1.getMyTask();
        int indexoF = -1;
        for (ToDoItem u:items  ) {
            if (u.getId() == task.getId()){
                indexoF = items.indexOf(u);
                break;
            }
        }
        if (indexoF == -1){
            items.add(task);
            taskId++;
        }else {
            items.set(indexoF, task);
        }
        userRepo.save(user1);
    }
    @Override
    public void updateTitle(UpdateTask updateTask) throws Exception {
        ToDoItem items = findTask(updateTask.getTaskId(), updateTask.getUserId());
        items.setTitle(updateTask.getItem());
        save(findTask(updateTask.getTaskId(), updateTask.getUserId()));
    }

    @Override
    public void updateDescription(UpdateTask updateTask) throws Exception {
        ToDoItem  item  = findTask(updateTask.getTaskId(), updateTask.getUserId());
        item.setDescription(updateTask.getItem());
        save(findTask(updateTask.getTaskId(), updateTask.getUserId()));
    }

    @Override
    public void markItemASComplete(int userId, int todoItemIds, boolean mark) throws Exception {
        ToDoItem  item  = findTask(todoItemIds, userId);
        item.setCompleted(mark);
        save(findTask(todoItemIds, userId));
    }

    @Override
    public void deleteTask(int userId, int todoItemIds) throws Exception{
        List<ToDoItem> toDoItems = (List<ToDoItem>) findAllUserTask(userId);
        for (ToDoItem toDoItem1 : toDoItems ) {
            if (toDoItem1.getId() == todoItemIds){
                toDoItems.remove(toDoItem1);
            }
        }
    }

}
