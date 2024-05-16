package com.TodoLists.Application.Data.Repository;

import com.TodoLists.Application.Data.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepoImplTest {
    @Autowired
    private UserRepoImpl userRepo;
    @Test
    void textThatYouCanReadFromObject(){
        assertEquals(1,userRepo.findAll().size());
    }
//    @Test
//    void textThatYouCanWriteToObject() throws Exception {
//        List<User> users = new ArrayList<>();
//        User user = new User();
//        user.setPassword("hello");
//        user.setUsername("israel");
//        users.add(user);
//        userRepo.writeToObject(users);
//        assertEquals(1, userRepo.findAll().size());
//    }

    @Test
    void textThatUserCanCreateNewUserSaved() throws Exception {
        User user = new User();
        user.setPassword("hello");
        user.setUsername("israel");
//        userRepo.save(user);
        System.out.println(userRepo.findAll());
//        assertEquals(3,userRepo.findAll().size());
    }

    @Test
    void  findAlls(){
//        System.out.println(userRepo.findAll());
        for(User u :userRepo.findAll()){
            System.out.println(u.toString());
        }
    }

    @Test
    void textThatSaveCreateNewUserIfItNotTheSameObject() throws Exception {
        User user = new User();
        user.setPassword("hello1");
        user.setUsername("israel");
        user.setId(1);
        // the update is when the user is finding and then updating
        assertEquals(1,userRepo.save(user).getId());
    }
    @Test
    void testThatUserCanFindByUserName() throws Exception {
        User user = new User();
        user.setPassword("hello");
        user.setUsername("israel");
        userRepo.save(user);
        User user1 = new User();
        user1.setPassword("hi");
        user1.setUsername("betty");
        user1.setId(2);
        userRepo.save(user1);
       assertEquals(user1, userRepo.findByUsername("betty"));
    }


//    @Test
//    void testThatUserCanFindId() throws Exception {
//        User user1 = new User();
//        user1.setPassword("hi");
//        user1.setUsername("betty");
//        user1.setId(2);
//        userRepo.save(user1);
//        assertEquals(user1, userRepo.findById(2));
//
//    }

    @Test
    void testThatUserCanDeleteAll() throws Exception {
        userRepo.deleteAll();
        assertEquals(0,userRepo.findAll().size());
    }


@Test
void findByIf() throws Exception {
    System.out.println(userRepo.findById(2));
}
    @Test
    void findAll(){
        assertEquals(0, userRepo.findAll().size());
    }

    @Test
    void advantage() throws Exception {
        User user = userRepo.findById(1);
        user.setEnable(true);
        userRepo.save(user);
    }
}