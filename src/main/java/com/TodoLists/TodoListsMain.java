package com.TodoLists;

import com.TodoLists.DTOs.Request.LoginRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@EnableScheduling
@SpringBootApplication
public class TodoListsMain implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TodoListsMain.class);

    // Initialize with default value
//    public static String FILENAME = "/tmp/user_data";
    public static String FILENAME = "C:\\Users\\Sober\\IdeaProjects\\TodoListApp\\src\\main\\java\\com\\TodoLists\\user";

    public static void main(String[] args) {
        SpringApplication.run(TodoListsMain.class, args);
    }


//    private static final String API_URL = "https://todolistapp-1-s2az.onrender.com/user/getMobileNavPackage/1";
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Scheduled(fixedRate = 10 * 60 * 1000)
////    @Scheduled(fixedRate = 2 * 60 * 1000)
//    public void callApiEvery10Minutes() {
//        try {
//            System.out.println("in call api meod: " + java.time.LocalDateTime.now());
//            restTemplate.getForObject(API_URL, String.class);
//            System.out.println("API called successfully at: " + java.time.LocalDateTime.now());
//        } catch (Exception e) {
//            System.err.println("Failed to call API: " + e.getMessage());
//        }
//    }

    @Override
    public void run(String... args) {
        try {
            String envPath = System.getenv("FILE_PATH");
            if (envPath != null && !envPath.trim().isEmpty()) {
                FILENAME = envPath.trim();
                logger.info("Using FILE_PATH from environment: {}", FILENAME);
            } else {
                logger.warn("FILE_PATH not set in environment, using default: {}", FILENAME);
            }

            Path path = Paths.get(FILENAME);
            Files.createDirectories(path.getParent());

            File file = path.toFile();
            if (file.createNewFile()) {
                logger.info("Created new file at: {}", path.toAbsolutePath());
            } else {
                logger.info("File already exists at: {}", path.toAbsolutePath());
            }

            if (!file.canWrite()) {
                throw new IOException("File is not writable: " + path.toAbsolutePath());
            }
//            callApiEvery10Minutes();

        } catch (IOException e) {
            logger.error("Failed to initialize data file", e);
            // Consider failing fast if file is critical for your application
            // System.exit(1);
        }
    }
}