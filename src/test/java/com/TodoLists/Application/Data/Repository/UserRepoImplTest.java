package com.TodoLists.Application.Data.Repository;

import com.TodoLists.Application.Data.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepoImplTest {
    @Autowired
    private UserRepoImpl userRepo;
    @Test
    void textThatYouCanReadFromObject(){
        assertEquals(1,userRepo.findAll().size());
    }
    @Test
    void textThatYouCanWriteToObject() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setPassword("hello");
        user.setUsername("israel");
        users.add(user);
        userRepo.writeToObject(users);
        textThatYouCanReadFromObject();
//        assertEquals();
    }

    @Test
    void textThatUserCanUpdateIfItTeSameUserElseItCreateNewBeSaved() throws Exception {
        User user = new User();
        user.setPassword("hello");
        user.setUsername("israel");
        userRepo.save(user);
        textThatYouCanReadFromObject();
    }

    @Test
    void textThatSaveCreateNewUserIfItNotTheSameObject() throws Exception {
        User user = new User();
        user.setPassword("hello");
        user.setUsername("israel");
        user.setId(1);
        userRepo.save(user);
        assertEquals(2,userRepo.findAll().size());
    }
    @Test
    void testThatUserCanFindByUserName() throws Exception {
        User user = new User();
        user.setPassword("hello");
        user.setUsername("israel");
        user.setId(1);
        userRepo.save(user);
        User user1 = new User();
        user1.setPassword("hi");
        user1.setUsername("betty");
        user1.setId(2);
        userRepo.save(user1);
       assertEquals(user1, userRepo.findByUsername("betty"));
    }

    @Test
    void testThatUserCanFindId() throws Exception {
        User user1 = new User();
        user1.setPassword("hi");
        user1.setUsername("betty");
        user1.setId(2);
        userRepo.save(user1);
        assertEquals(user1, userRepo.findById(2));

    }

    @Test
    void testThatUserCanDeleteAll() throws Exception {
        userRepo.deleteAll();
        assertEquals(0,userRepo.findAll().size());
    }

    @Test
    void findAll(){
        assertEquals(0, userRepo.findAll().size());
    }

}