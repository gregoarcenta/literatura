package org.arcentales.literatura;

import org.arcentales.literatura.services.LibraryService;
import org.arcentales.literatura.utils.ConsoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    @Autowired
    LibraryService service;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ConsoleMenu menu = new ConsoleMenu(service);
        menu.start();
    }
}
