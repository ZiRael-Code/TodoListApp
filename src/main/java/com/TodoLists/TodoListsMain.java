package com.TodoLists;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
@SpringBootApplication
public class TodoListsMain implements CommandLineRunner{
    public static final String FILENAME = System.getenv("FILE_PATH") != null ? System.getenv("FILE_PATH") : "C:\\Users\\Israel\\Desktop\\TodoListApp\\src\\main\\java\\user";
    public static void main(String[] args) {
        SpringApplication.run(TodoListsMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        File file = new File(FILENAME);

        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
           boolean fileCreated =  file.createNewFile();
//            if (!fileCreated){
//
//            }


        }








        Path path = Path.of(FILENAME);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    }
