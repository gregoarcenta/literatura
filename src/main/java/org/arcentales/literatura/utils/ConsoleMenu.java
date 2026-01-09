package org.arcentales.literatura.utils;

import org.arcentales.literatura.models.BookData;
import org.arcentales.literatura.services.GutendexApiService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner;
    private GutendexApiService service;
    private InputValidator validator;
    private boolean running;

    public ConsoleMenu(GutendexApiService apiService) {
        scanner = new Scanner(System.in);
        validator = new InputValidator(scanner);
        service = apiService;
        running = true;
    }

    public void start() {
        displayWelcome();

        while (running) {
            displayMainMenu();
            int choice = validator.getMenuOption(1, 2);
            handleMenuChoice(choice);
        }

        displayGoodbye();
        scanner.close();
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                List<BookData> books = service.getBooks();
                System.out.println("DATITA: " + books.getFirst().title());
                break;
            case 2:
                running = false;
                System.exit(0);
                break;
            default:
                System.out.println("Opción invalida. Por favor intente nuevamente.");
        }
    }

    private void displayWelcome() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║       LiterAlura Challenge        ║");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.println();
    }

    private void displayMainMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("              MAIN MENU");
        System.out.println("----------------------------------------");
        System.out.println("1. Obtener libros");
        System.out.println("2. Salir");
        System.out.println("----------------------------------------");

    }

    private void displayGoodbye() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  Gracias por usar LiterAlura Challenge!  ║");
        System.out.println("║         Que tengas un buen dia!          ║");
        System.out.println("╚══════════════════════════════════════════╝\n");
    }
}
