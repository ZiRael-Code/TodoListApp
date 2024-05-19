package com.TodoLists.Data.Repository;

import com.TodoLists.Data.Model.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepoImpl implements UserRepo {

    private final String FILENAME = "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\user.dat";
    private int userId = 1;


    public User findById(int userId) throws Exception {
        List<User> users = findAll();
        User user = null;
        for (User user1 :users) {
            if (userId  == user1.getId()){
                user = user1;
            }
        }
        return user;
    }

    @Override
    public void deleteAll() throws Exception {
        List<User> users = new ArrayList<>();
        writeToObject(users);
    }


    public List<User> findAll() {
        List<User> findAllUsers = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                findAllUsers = (List<User>) objectInputStream.readObject();


        } catch (EOFException e) {
           return findAllUsers;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        return findAllUsers;
    }

    @Override
    public User save(User user) throws Exception {
//        ver = new Ver<>();
       List<User> saveUsers = findAll();
       User user1 = user;
//       user1.setId(userId);
       int indexoF = -1;
        for (User u:saveUsers  ) {
            if (u.getId() == user.getId()){
                indexoF = saveUsers.indexOf(u);
                break;
            }
        }
        if (indexoF == -1){
            int lol = -1;
            if ((saveUsers.isEmpty())) {
                lol = 0;
            }else {
                lol =  saveUsers.get(saveUsers.size()-1).getId();
            }
            lol+=1;
            user1.setId(lol);
            saveUsers.add(user);
//            userId++;

        }else {
            saveUsers.set(indexoF, user1);
        }

        writeToObject(saveUsers);
        return user1;
    }

    public void writeToObject(List<User> users) throws Exception {
        if (Path.of(FILENAME).isAbsolute()) {
            try (ObjectOutputStream objectInputStream
                         = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                objectInputStream.writeObject(users);
            } catch (IOException e) {
                throw new Exception("Error: " + e.getMessage());
            }
        }else {
            throw new RuntimeException("File not created");
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

    @Override
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        for (User user: findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        throw new Exception("User not found");
    }

}
