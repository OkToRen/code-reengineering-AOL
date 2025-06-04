package main;

import java.util.ArrayList;
import java.util.Scanner;

import helper.ConsoleHelper;
import model.User;
import model.UserRole;

public class AuthService {
    public static final ArrayList<User> users = new ArrayList<>();
    public static User currentUser = null;
    private final Scanner sc = new Scanner(System.in);
    private static int userIdCounter = 1;
    
    

    public void showLoginMenu() {
    	ConsoleHelper.printHeader("LOGIN MENU");
        ConsoleHelper.print("1. Register");
        ConsoleHelper.print("2. Login");
        ConsoleHelper.print("3. Exit");
        ConsoleHelper.printInline("Choose option: ");
        
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                ConsoleHelper.print("Goodbye!");
                System.exit(0);
                break;
            default:
                ConsoleHelper.printError("Invalid choice!");
        }
    }

    public void registerUser() {
		ConsoleHelper.printHeader("USER REGISTRATION");
        
		ConsoleHelper.printInline("Enter name: ");
        String name = sc.nextLine();
        
        ConsoleHelper.printInline("Enter password: ");
        String password = sc.nextLine();
        
        ConsoleHelper.printInline("Enter email: ");
        String email = sc.nextLine();
        
        ConsoleHelper.printInline("Enter age: ");
        Integer age = sc.nextInt();
        sc.nextLine();
        
        ConsoleHelper.printInline("Enter phone number: ");
        String phoneNumber = sc.nextLine();

        User newUser = new User(++userIdCounter, name, password, email, age, phoneNumber, UserRole.USER);
        
        users.add(newUser);
        ConsoleHelper.print("Registration successful! User ID: " + newUser.getId());
    }

    public void loginUser() {
    	ConsoleHelper.printHeader("USER LOGIN");
    	ConsoleHelper.printInline("Enter name: ");
        String name = sc.nextLine();
        ConsoleHelper.printInline("Enter password: ");
        String password = sc.nextLine();
        
        for (User u : users) {
            if (u.getName().equals(name) && u.getPassword().equals(password)) {
                if (u.isActive()) {
                    currentUser = u;
                    ConsoleHelper.print("Login successful! Welcome, " + u.getName());
                    return;
                } else {
                    ConsoleHelper.printError("Account is inactive!");
                    return;
                }
            }
        }
        ConsoleHelper.printError("Invalid credentials!");
    }

    public void seedAdmin() {
    	User admin = new User(++userIdCounter, "admin", "admin123", "admin@movie.com", 30, "1234567890", UserRole.ADMIN);
        users.add(admin);
    }

    public static int getNextUserId() {
        return ++userIdCounter;
    }
}
