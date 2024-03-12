//package com.TodoLists.Application.Data.Repository;
//
//import com.TodoLists.Application.Data.Model.ToDoItem;
//import com.TodoLists.Application.Data.Model.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//@Repository
//public class TodoListImpli implements TodoListRepo{
//    private int taskId;
//    private final String FILENAME = "C:\\Users\\Israel\\IdeaProjects\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\todoItem.dat";
//
//
//    public ToDoItem findById(int id) throws Exception {
//        List<ToDoItem> items = findAll();
//        for (ToDoItem item :items) {
//            if (id  == item.getId()){
//                return item;
//            }else {
//                throw new Exception("User not found");
//            }
//        }return null;
//    }
//
//    @Override
//    public void deleteAll() throws Exception {
//        List<ToDoItem> items = new ArrayList<>();
//        writeToObject(items);
//    }
//
//    public List<ToDoItem> findAll() {
//        List<ToDoItem> findAllTask = new ArrayList<>();
//        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
//            findAllTask =  (List<ToDoItem>) objectInputStream.readObject();
//        } catch (EOFException e) {
//            return findAllTask;
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        return findAllTask;
//    }
//
//    @Override
//    public ToDoItem findTask(int taskId) throws Exception {
//        return null;
//    }
//
//    @Override
//    public void save(ToDoItem task) throws Exception {
//        List<ToDoItem> items =  findAll();
//        task.setId(taskId);
//        taskId++;
//        int indexoF = -1;
//        for (ToDoItem u:items  ) {
//            if (u.getId() == task.getId()){
//                indexoF = items.indexOf(u);
//                break;
//            }
//        }
//        if (indexoF == -1){
//            items.add(task);
//        }else {
//            items.set(indexoF, task);
//        }
//        writeToObject(items);
//    }
//
//    public void writeToObject(List<ToDoItem> items) throws Exception {
//        try (ObjectOutputStream objectInputStream
//                     = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
//            objectInputStream.writeObject(items);
//
//        } catch (IOException e) {
//            throw new Exception("Error: " + e.getMessage());
//        }
//    }
//    @Override
//    public void deleteAccountById(int id) throws Exception {
//        ToDoItem u = findById(id);
//        List<ToDoItem> users = findAll();
//        users.remove(u);
//        writeToObject(users);
//    }
//
//    @Override
//    public List<ToDoItem> findAllPriority() {
//        return null;
//    }
//}
//
//
//
