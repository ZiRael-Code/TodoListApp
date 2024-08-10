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
        String remove = "\"1. **Expounding Particular Aspects**: Take some of the concepts or case studies that had the most impact and elaborate on them a bit more. You could, for example, write about a case study that deals with predictive control and how that panned out in actual life.\n" +
                "2. **Personal Reflections**: Expand on the personal reflections and in what manner the module has changed your mindset. Give more specific examples of how your thinking shifted.\n" +
                "3. **Group Dynamics**: Explain the dynamics of the group you were part of, including people's roles and some of the specific challenges you encountered and how you managed to overcome them.\n" +
                "4. **Future Plans**: explain how you plan to apply the skills you have obtained in your current or future career. Mention any specific business ideas or projects you are now planning to pursue.\n" +
                "\n" +
                "This is an extended version that includes these components:\n" +
                "---\n" +
                "\n" +
                "### Reflection: Entrepreneurship Module\n" +
                "\n" +
                "#### Introduction\n" +
                "Taking up this module of business and management with a focus on entrepreneurship has been a real transformational journey for me. At the beginning, little was known about entrepreneurship, and my attitude was a mixture of curiosity and skepticism. However, this attitude changed over time as I did develop the module. This reflective piece shall cover my initial attitudes, the insights gained, delivery and facilitation of the module, my experiences in group work, and how I plan to apply this newfound knowledge.\n" +
                "\n" +
                "#### Initial Attitudes Towards Enterprise\n" +
                "Before the module, I used to think that entrepreneurship is a very difficult and fairly risky venture that can only be pursued by creative genius and well-financed people. Much of it had to do with the external factors beyond my control in achieving success. This perception created hesitation and fear, to a certain extent, for engaging in any entrepreneurial activity.\n" +
                "\n" +
                "#### Insights and Key Learnings\n" +
                "The module has opened my eyes to a number of critical aspects of entrepreneurship that were blind to me. One of the most enlightened concepts in my understanding was predictive control, which includes everything about strategic planning and foresight on how to manage future uncertainties. Only the realization that it is impossible to control the future but we can only prepare and adapt through predictive strategies made me understand. For example, one case story about a tech startup explained how predictive control allowed that company to pivot in reaction to market changes and ultimately to succeed.\n" +
                "\n" +
                "Another deep learning was the power of mindset. The fact that a growth mindset, founded on resilience, optimism, and learning from failures, was brought forth as key for an entrepreneur. It shifted my view of failures from a setback to an opportunity for changing, growing, and learning. One exercise on the failures and consequent successes of famous entrepreneurs like Steve Jobs and Elon Musk really hit the nail home.\n" +
                "\n" +
                "I also learned the importance of self-belief and the keeping of a positive outlook. The thought that as little as 1% hope may keep one going and innovating was most inspiring. These insights collectively broadened my understanding of entrepreneurship beyond business acumen to include psychological and emotional resilience.\n" +
                "\n" +
                "#### Module Delivery and Facilitation\n" +
                "The mode of delivery of the module was very engaging and interactive, and this helped a lot in my learning. In fact, the facilitator's approach—linking theory with practice—really helped to close the gap between the concept and the practice. Interactive lectures, case examples from real life, and guest speakers added different perspectives and value to the learning.\n" +
                "\n" +
                "What really made a difference for me was the facilitator's focus on encouragement and development of a brave mindset. Their ability to create an environment for inclusive learning helped me to share my thoughts and ask questions without any hesitation, which improved my comprehension and confidence levels tremendously. Developing business models was the most enjoyable part, as we were pushed out of our comfort zones to think creatively and fight established wisdom.\n" +
                "\n" +
                "#### Group Work and In-Class Exercises\n" +
                "Working on business ideas in groups was a very enriching part of the module. At the start, I underestimated the potential of group work; that is to say, my ideas were too simplistic or impractical. However, with discussions and brainstorming display within the group, it became known how collective intelligence worked.\n" +
                "\n" +
                "There was one case in which my group could refine and develop an idea that was initially thought to be very trivial. Various points of view and critical feedback taken from my group members made a workable business idea out of a simple concept. It taught me the importance of teamwork and how many different viewpoints could add a great deal to creativity and problem-solving. My group was working on a sustainable packaging solution, and each member added to the idea from his or her background, making it very strong and effective.\n" +
                "\n" +
                "In-class exercises also formed an important part in consolidating theoretical input. The in-class exercises provided hands-on experience in applying entrepreneurial principles with respect to market analysis, business model development, and financial planning. They helped in building practical skills like effective communication, negotiation, and time management. One of the in-class exercises that perhaps has remained in my memory is the one whereby we were required to pitch our business idea to a panel of prospective investors. It turned out to be highly challenging yet rewarding.\n" +
                "\n" +
                "#### Applying the Knowledge Moving Forward\n" +
                "The module impacted me with extensive knowledge and skills in entrepreneurship and business management. I hope to apply knowledge I have learned about predictive control and strategic planning in my future endeavors. The understanding of the necessity for forecasting and preparing for uncertainties is quite instrumental in the dynamic business environment on how to surmount such challenges.\n" +
                "\n" +
                "Equally important will be the insights on mindset and self-belief throughout my entrepreneurial journey. Adopting the growth mindset and how to stay resilient after each fall will motivate and focus me on my long-term goals. I will equally use the power of teamwork and collaboration in all my future projects since diverse perspectives and collective efforts mostly give better results.\n" +
                "\n" +
                "In the current job, I will conduct more collaborative brainstorming sessions so that each and every single voice within the team is being heard. This approach, inspired by the group work experience in the module, would enhance innovation and problem-solving as a group. Furthermore, I am now considering a side project where I could apply the sustainable packaging solution we have developed during the module in order to take part in environmental sustainability and explore entrepreneurial opportunities.\n" +
                "\n" +
                "#### Conclusion\n" +
                "Looking back into this entrepreneurship module, I realize that much of my thoughts and attitude toward entrepreneurship have changed. It gave me so much critical knowledge, practical skills, and most importantly, renovated my confidence and positivism. These experiences and insights shall surely be very character-building and one day form the foundation of being an aspiring entrepreneur.\n" +
                "\n" +
                "This reflective piece has helped me in critically reflecting upon the journey of my learning, bringing out bits which have influenced my views and how I intend to apply them moving forward. The module has equipped me not only with invaluable business acumen but also furnished me with a kind of mindset that will drive my entrepreneurial aspirations. Gained insight will be a springboard of future business ventures, enabling me to travel through challenges and opportunities with confidence and resilience.\n" +
                "\n" +
                "---\n" +
                "\n" +
                "\"\n" +
                "https://www.humanizeai.pro/#:~:text=1.%20**Expounding%20Particular,confidence%20and%20resilience.%0A%0A%2D%2D%2D";

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