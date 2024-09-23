package com.TodoLists;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@SpringBootApplication
public class TodoListsMain implements CommandLineRunner{
    public static final String FILENAME = System.getenv("FILE_PATH");

    public static void main(String[] args) {
        SpringApplication.run(TodoListsMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            File file = new File(FILENAME);
            System.out.println("Attempting to create file at: " + file.getAbsolutePath());

            if (!file.exists()) {
                System.out.println("File does not exist. Creating new file...");
                file.createNewFile();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
