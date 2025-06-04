package main;

import java.util.Scanner;

import helper.ConsoleHelper;
import model.User;
import model.UserRole;

public class UserService {
    private final Scanner sc = new Scanner(System.in);

    public void viewProfile() {
        User currentUser = AuthService.currentUser;
        ConsoleHelper.printHeader("USER PROFILE");
        ConsoleHelper.print("ID: " + currentUser.getId());
        ConsoleHelper.print("Name: " + currentUser.getName());
        ConsoleHelper.print("Email: " + currentUser.getEmail());
        ConsoleHelper.print("Age: " + currentUser.getAge());
        ConsoleHelper.print("Phone: " + currentUser.getPhoneNumber());
        ConsoleHelper.print("Role: " + currentUser.getRole());
        ConsoleHelper.print("Status: " + (currentUser.isActive() ? "Active" : "Inactive"));
        ConsoleHelper.print("Registration Date: " + currentUser.getRegistrationDate());
    }

    public void updateProfile() {
    	User currentUser = AuthService.currentUser;
    	ConsoleHelper.printHeader("\n=== UPDATE PROFILE ===");
        ConsoleHelper.printInline("Enter new email (current: " + currentUser.getEmail() + "): ");
        String newEmail = sc.nextLine();
        if (!newEmail.trim().isEmpty()) {
            currentUser.setEmail(newEmail);
        }
        
        ConsoleHelper.printInline("Enter new age (current: " + currentUser.getAge() + "): ");
        String ageInput = sc.nextLine();
        if (!ageInput.trim().isEmpty()) {
            currentUser.setAge(Integer.parseInt(ageInput));
        }
        
        ConsoleHelper.printInline("Enter new phone (current: " + currentUser.getPhoneNumber() + "): ");
        String newPhone = sc.nextLine();
        if (!newPhone.trim().isEmpty()) {
            currentUser.setPhoneNumber(newPhone);
        }
        
        ConsoleHelper.print("Profile updated successfully!");
    }

    public void viewAllUsers() {
        if (!AuthService.currentUser.getRole().equals(UserRole.ADMIN)) {
            ConsoleHelper.printError("Access denied!");
            return;
        }

        ConsoleHelper.printHeader("ALL USERS");
        for (User u : AuthService.users) {
            ConsoleHelper.print("ID: " + u.getId() + ", Name: " + u.getName() + ", Role: " + u.getRole() + ", Active: " + u.isActive());
        }
    }
}
