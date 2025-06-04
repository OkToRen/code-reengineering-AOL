package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.User;
import model.UserRole;

public class AuthService {
    public static final ArrayList<User> users = new ArrayList<>();
    public static User currentUser = null;
    private final Scanner sc = new Scanner(System.in);
    private static int userIdCounter = 1;
    
    

    public void showLoginMenu() {
    	System.out.println("\n=== LOGIN MENU ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        
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
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void registerUser() {
System.out.println("\n=== USER REGISTRATION ===");
        
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        
        System.out.print("Enter age: ");
        Integer age = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();

        User newUser = new User(++userIdCounter, name, password, email, age, phoneNumber, UserRole.USER);
        
        users.add(newUser);
        System.out.println("Registration successful! User ID: " + newUser.getId());
    }

    public void loginUser() {
    	System.out.println("\n=== USER LOGIN ===");
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        for (User u : users) {
            if (u.getName().equals(name) && u.getPassword().equals(password)) {
                if (u.isActive()) {
                    currentUser = u;
                    System.out.println("Login successful! Welcome, " + u.getName());
                    return;
                } else {
                    System.out.println("Account is inactive!");
                    return;
                }
            }
        }
        System.out.println("Invalid credentials!");
    }

    public void seedAdmin() {
    	User admin = new User(++userIdCounter, "admin", "admin123", "admin@movie.com", 30, "1234567890", UserRole.ADMIN);
        users.add(admin);
    }

    public static int getNextUserId() {
        return ++userIdCounter;
    }
}
