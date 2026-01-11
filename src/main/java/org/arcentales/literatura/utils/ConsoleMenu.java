package org.arcentales.literatura.utils;

import org.arcentales.literatura.models.BookData;
import org.arcentales.literatura.services.LibraryService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator;
    private final LibraryService libraryService;

    private List<BookData> lastSearchResults;
    private boolean running = true;

    public ConsoleMenu(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.validator = new InputValidator(scanner);
    }

    public void start() {
        displayWelcome();

        while (running) {
            displayMainMenu();
            int choice = validator.getMenuOption(1, 4);
            handleMenuChoice(choice);
        }

        displayGoodbye();
        scanner.close();
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {

            case 1 -> searchExternalBooks();

            case 2 -> saveBookFromLastSearch();

            case 3 -> listMyBooks();

            case 4 -> running = false;

            default -> System.out.println("Opción inválida.");
        }
    }

    /* ---------- OPCIONES ---------- */

    private void searchExternalBooks() {
        System.out.print("Buscar en Gutendex: ");
        String query = scanner.nextLine();
        query = query.trim().replace(" ", "%20");

        lastSearchResults = libraryService.searchExternal(query);

        if (lastSearchResults.isEmpty()) {
            System.out.println("No se encontraron resultados.");
            return;
        }

        lastSearchResults.forEach(book ->
                                          System.out.println(book.id() + " - " + book.title())
        );
    }

    private void saveBookFromLastSearch() {
        if (lastSearchResults == null || lastSearchResults.isEmpty()) {
            System.out.println("Primero realiza una búsqueda.");
            return;
        }

        System.out.print("Ingrese el ID del libro a guardar: ");
        long id = Long.parseLong(scanner.nextLine());

        BookData selected = lastSearchResults.stream()
                .filter(b -> b.id().equals(id))
                .findFirst()
                .orElse(null);

        if (selected == null) {
            System.out.println("ID no encontrado en los resultados.");
            return;
        }

        libraryService.saveFromGutendex(selected);
        System.out.println("Libro guardado correctamente.");
    }

    private void listMyBooks() {
        libraryService.listMyBooks().forEach(book ->
                                                     System.out.println(
                                                             book.getTitle() + " - " + book.getAuthor().getName()
                                                     )
        );
    }

    /* ---------- UI ---------- */
    private void displayWelcome() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║       LiterAlura Challenge        ║");
        System.out.println("╚═══════════════════════════════════╝");
    }

    private void displayMainMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("1. Buscar libros en Gutendex");
        System.out.println("2. Guardar libro en mi biblioteca");
        System.out.println("3. Ver mis libros");
        System.out.println("4. Salir");
        System.out.println("----------------------------------------");
    }

    private void displayGoodbye() {
        System.out.println("\nGracias por usar LiterAlura.");
    }
}
