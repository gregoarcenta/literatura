package org.arcentales.literatura.utils;

import java.util.Scanner;

public record InputValidator(Scanner scanner) {
    public int getMenuOption(int min, int max) {
        while (true) {
            try {
                System.out.print("Elige una opción: ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("La entrada no puede estar vacía. Por favor intente nuevamente");
                    continue;
                }

                int option = Integer.parseInt(input);

                if (option >= min && option <= max) {
                    return option;
                } else {
                    System.out.printf("Por favor ingrese un numero entre %d y %d.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Por favor ingrese un numero valido");
            }
        }
    }

}
