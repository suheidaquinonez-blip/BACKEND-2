package com.company.coursemanagement;

import com.company.coursemanagement.presentation.ConsoleMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGestionDeCursosApplication implements CommandLineRunner {

    private final ConsoleMenu consoleMenu;

    public ApiGestionDeCursosApplication(ConsoleMenu consoleMenu) {
        this.consoleMenu = consoleMenu;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGestionDeCursosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleMenu.start();
    }
}