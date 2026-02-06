package org.example;


import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.util.Scanner;

import static org.example.InputHelper.*;


public class Main {


    public static final String ERROR_WITH_DATABASE_CONNECTION = "Some problems with database connection";

    static void main() {
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                1. Create user
                2. Get user by id
                3. Get all users
                4. Update user
                5. Delete user
                0. Exit
                """);

            int choice = scanner.nextInt();

            switch (choice) {

                case 1 -> {
                    User user = new User();

                    user.setName(readString(scanner, "Name: "));
                    user.setEmail(readString(scanner, "Email: "));

                    Integer age = readInt(scanner, "Age: ");
                    if (age == null || age <= 0) {
                        System.out.println("Age must be positive!");
                        break;
                    }
                    user.setAge(age);

                    try {
                        userService.create(user);
                    } catch (Exception e) {
                        System.out.println(ERROR_WITH_DATABASE_CONNECTION);
                    }
                }

                case 2 -> {
                    Long id = readLong(scanner, "ID: ");
                    if (id == null) break;

                    try {
                        System.out.println(userService.getById(id));
                    } catch (Exception e) {
                        System.out.println(ERROR_WITH_DATABASE_CONNECTION);
                    }
                }

                case 3 -> {
                    try {
                        userService.getAll().forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println(ERROR_WITH_DATABASE_CONNECTION);
                    }
                }

                case 4 -> {
                    Long id = readLong(scanner, "ID: ");
                    if (id == null) break;

                    User user = null;
                    try {
                        user = userService.getById(id);
                    } catch (Exception e) {
                        System.out.println(ERROR_WITH_DATABASE_CONNECTION);
                        break;
                    }
                    if (user == null) {
                        System.out.println("User not found");
                        break;
                    }

                    user.setName(readString(scanner, "New name: "));
                    try {
                        userService.update(user);
                    } catch (Exception e) {
                        System.out.println(ERROR_WITH_DATABASE_CONNECTION);
                    }
                }

                case 5 -> {
                    Long id = readLong(scanner, "ID: ");
                    if (id == null) break;

                    try {
                        userService.delete(id);
                    } catch (Exception e) {
                        System.out.println(ERROR_WITH_DATABASE_CONNECTION);
                    }
                }

                case 0 -> System.exit(0);

                default -> System.out.println("Unknown option");
            }
        }
    }
}
