package com.TodoLists.Application.Data.Repository;

import com.TodoLists.Application.Data.Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepo {
    List<User> findAll() throws Exception;
    void writeToObject(List<User> users) throws Exception;
    void save(User user) throws Exception;
    User findByUsername(String username) throws Exception;
    void deleteAccountByUsernameAndPassword(User user) throws Exception;
    void deleteAccountById(int id) throws Exception;
    User findById(int id) throws Exception;
    void deleteAll() throws Exception;
}
