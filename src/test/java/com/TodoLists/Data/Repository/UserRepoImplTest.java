package com.TodoLists.Data.Repository;

import com.TodoLists.Data.Model.User;
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
        user.setPassword("israel");
        user.setUsername("israel");
        user.setEmail("israel");
        userRepo.save(user);
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
    System.out.println(userRepo.findById(1));
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

    public static void main(String[] args) {
        String remove = "### Privacy and Policy Document\n" +
                "\n" +
                "**Introduction**\n" +
                "This Privacy and Policy document outlines the expectations and responsibilities for students enrolled in our courses. By participating in the program, students agree to adhere to the guidelines and policies set forth below.\n" +
                "\n" +
                "---\n" +
                "\n" +
                "**1. General Conduct**\n" +
                "- Students must treat all instructors and fellow students with respect. Any form of rudeness, harassment, or disrespect towards instructors or peers will not be tolerated.\n" +
                "  \n" +
                "**2. Attendance and Punctuality**\n" +
                "- Students are required to attend all classes and be on time. If a student cannot attend a class or will be late, they must notify the instructor in advance with a valid reason. Failure to do so may result in disciplinary action.\n" +
                "\n" +
                "**3. Use of Electronic Devices**\n" +
                "- The use of mobile phones or other electronic devices is discouraged during class sessions, except for academic purposes. Students should limit phone usage to avoid distractions.\n" +
                "\n" +
                "**4. Weekly Evaluation Tests**\n" +
                "- Weekly evaluation tests or exams will be administered at the beginning of each week. These tests can be given at any time during the session. Students are expected to be prepared and take these tests seriously.\n" +
                "\n" +
                "**5. Assignments**\n" +
                "- Assignments will be given every day and must be completed on time. Late submissions may result in penalties or a reduction in grade.\n" +
                "\n" +
                "**6. Milestone Projects**\n" +
                "- Students will be required to complete a milestone project, such as a full-stack application, as part of the course requirements. This project will be evaluated and will contribute to the final grade.\n" +
                "\n" +
                "**7. Refund Policy**\n" +
                "- Refunds are not permitted once the course has started or if an agreement has been made. Students are expected to commit fully to the course once they have enrolled.\n" +
                "\n" +
                "**8. Prohibited Items**\n" +
                "- Students are prohibited from bringing any form of weapon or dangerous item to the class sessions. Violation of this rule will lead to immediate disciplinary action, including possible expulsion.\n" +
                "\n" +
                "**9. Disciplinary Actions**\n" +
                "- Any violation of the policies outlined in this document may result in disciplinary action, including but not limited to warnings, suspension, or expulsion from the course.\n" +
                "\n" +
                "---\n" +
                "\n" +
                "**Conclusion**\n" +
                "By enrolling in the course, students agree to abide by these policies and understand the consequences of any violations. These guidelines are in place to ensure a safe, respectful, and productive learning environment for all participants.";

        StringBuilder rewriteors = new StringBuilder();
        for (int rem = 0; rem < remove.length(); rem++) {
            char c = remove.charAt(rem);
            if (c == '-' || c == '#' || c == '*'){
                continue;
            }else {
                rewriteors.append(c);
            }
        }
        System.out.println(rewriteors);
    }
}