package com.TodoLists.Application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
@SpringBootApplication
public class Main implements CommandLineRunner {
//    private static final String DIRECTORY = "C:\\Users\\Israel\\IdeaProjects\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\UserFile";
    private static final String FILENAME = "C:\\Users\\Israel\\IdeaProjects\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\user.dat";
    private final String TASKFILENAME = "C:\\Users\\Israel\\IdeaProjects\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\todoItem.dat";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Path path = Path.of(FILENAME);
        Path path1 = Path.of(TASKFILENAME);
        if (!Files.exists(path) && Files.exists(path1)) {
                Files.createFile(path);
                Files.createFile(path1);
        }
    }
    }
