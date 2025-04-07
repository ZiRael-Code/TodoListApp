package com.TodoLists;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class TodoListsMain implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TodoListsMain.class);

    // Initialize with default value
    public static String FILENAME = "/tmp/user_data";

    public static void main(String[] args) {
        SpringApplication.run(TodoListsMain.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            // 1. Check environment variable first
            String envPath = System.getenv("FILE_PATH");
            if (envPath != null && !envPath.trim().isEmpty()) {
                FILENAME = envPath.trim();
                logger.info("Using FILE_PATH from environment: {}", FILENAME);
            } else {
                logger.warn("FILE_PATH not set in environment, using default: {}", FILENAME);
            }

            // 2. Create parent directories if they don't exist
            Path path = Paths.get(FILENAME);
            Files.createDirectories(path.getParent());

            // 3. Create file if it doesn't exist
            File file = path.toFile();
            if (file.createNewFile()) {
                logger.info("Created new file at: {}", path.toAbsolutePath());
            } else {
                logger.info("File already exists at: {}", path.toAbsolutePath());
            }

            // 4. Verify file is writable
            if (!file.canWrite()) {
                throw new IOException("File is not writable: " + path.toAbsolutePath());
            }

        } catch (IOException e) {
            logger.error("Failed to initialize data file", e);
            // Consider failing fast if file is critical for your application
            // System.exit(1);
        }
    }
}