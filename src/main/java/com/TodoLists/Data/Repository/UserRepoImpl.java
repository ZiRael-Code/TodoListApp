package com.TodoLists.Data.Repository;

import com.TodoLists.Data.Model.User;
import com.TodoLists.TodoListsMain;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepoImpl implements UserRepo {

    private final String FILENAME = TodoListsMain.FILENAME;
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
        System.out.println("__________________\nhere is the user you gave to me"+ user);
//        ver = new Ver<>();
       List<User> saveUsers = findAll();
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
            user.setId(lol);
            saveUsers.add(user);
//            userId++;

        }else {
            saveUsers.set(indexoF, user);
        }
        User u = writeToObject(saveUsers);
        System.out.println("__________________\nsaving user successfully"+ u);

        return u;
    }

    public User writeToObject(List<User> users) throws Exception {
        if (Path.of(FILENAME).isAbsolute()) {
            try (ObjectOutputStream objectInputStream
                         = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                objectInputStream.writeObject(users);
                return !users.isEmpty() ? findAll().get(findAll().size() - 1) : null;
            } catch (IOException e) {
                throw new Exception("Error: " + e.getMessage());
            }

        }else {
            Files.createFile(Path.of(FILENAME));
            throw new RuntimeException("File just created try again");
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
