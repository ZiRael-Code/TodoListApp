package com.TodoLists.Application.Data.Repository;

import com.TodoLists.Application.Data.Model.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepoImpl implements UserRepo {
    private final String FILENAME = "C:\\Users\\Israel\\IdeaProjects\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\user.dat";
    private int userId = 1;


    public User findById(int id) throws Exception {
        List<User> users = findAll();
        for (User user1 :users) {
            if (id  == user1.getId()){
                return user1;
            }else {
                throw new Exception("User not found");
            }
        }return null;
    }

    @Override
    public void deleteAll() throws Exception {
        List<User> users = new ArrayList<>();
        writeToObject(users);
    }


    public List<User> findAll() {
        List<User> findAllUsers = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                findAllUsers =  (List<User>) objectInputStream.readObject();
        } catch (EOFException e) {
           return findAllUsers;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return findAllUsers;
    }

    @Override
    public void save(User user) throws Exception {
       List<User> saveUsers = new ArrayList<>();
       saveUsers = findAll();
       user.setId(userId);
//       userId++;
       int indexoF = -1;
        for (User u:saveUsers  ) {
            if (u.getId() == user.getId()){
                indexoF = saveUsers.indexOf(u);
                break;
            }
        }
        if (indexoF == -1){
            saveUsers.add(user);
            userId++;
        }else {
            saveUsers.set(indexoF, user);
        }
        writeToObject(saveUsers);
    }

    public void writeToObject(List<User> users) throws Exception {
            try (ObjectOutputStream objectInputStream
                         = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                objectInputStream.writeObject(users);
            } catch (IOException e) {
                throw new Exception("Error: " + e.getMessage());
            }
    }
        @Override
    public void deleteAccountById(int id) throws Exception {
        User u = findById(id);
        List<User> users = findAll();
        users.remove(u);
        writeToObject(users);
    }

    @Override
    public void deleteAccountByUsernameAndPassword(User user) throws Exception {
        int index = -1;
        for (User a: findAll()) {
            if(!a.getUsername().equals(user.getUsername()) && a.getPassword().equals(user.getPassword())){
                throw new RuntimeException();
            }else {
                index=a.getId();
            }
        }
        if (index != -1) {
            deleteAccountById(index);
        }
    }

    @Override
    public User findByUsername(String username) throws Exception {
        for (User user: findAll()) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        throw new Exception("User not found");
    }

    public void writeTask(List<User> users) throws Exception {


    }
}
