package com.TodoLists.Application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
@SpringBootApplication
public class Main implements CommandLineRunner{
    private static final String FILENAME = "C:\\Users\\Israel\\MyFile\\TodoLists\\src\\main\\java\\com\\TodoLists\\Application\\user.dat";
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Path path = Path.of(FILENAME);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
    }
