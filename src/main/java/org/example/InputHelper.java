package org.example;

import java.util.Scanner;

final class InputHelper {
    private InputHelper() {
    }

    public static Integer readInt(Scanner scanner, String message) {
        System.out.print(message);
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid number!");
            scanner.next();
            return null;
        }
        return scanner.nextInt();
    }

    public static Long readLong(Scanner scanner, String message) {
        System.out.print(message);
        if (!scanner.hasNextLong()) {
            System.out.println("Invalid ID!");
            scanner.next();
            return null;
        }
        return scanner.nextLong();
    }

    public static String readString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.next();
    }
}
