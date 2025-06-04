package main;

import java.util.Scanner;

import model.User;
import model.UserRole;

public class UserService {
    private final Scanner sc = new Scanner(System.in);

    public void viewProfile() {
        User currentUser = AuthService.currentUser;
        System.out.println("\n=== USER PROFILE ===");
        System.out.println("ID: " + currentUser.getId());
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Age: " + currentUser.getAge());
        System.out.println("Phone: " + currentUser.getPhoneNumber());
        System.out.println("Role: " + currentUser.getRole());
        System.out.println("Status: " + (currentUser.isActive() ? "Active" : "Inactive"));
        System.out.println("Registration Date: " + currentUser.getRegistrationDate());
    }

    public void updateProfile() {
    	User currentUser = AuthService.currentUser;
    	System.out.println("\n=== UPDATE PROFILE ===");
        System.out.print("Enter new email (current: " + currentUser.getEmail() + "): ");
        String newEmail = sc.nextLine();
        if (!newEmail.trim().isEmpty()) {
            currentUser.setEmail(newEmail);
        }
        
        System.out.print("Enter new age (current: " + currentUser.getAge() + "): ");
        String ageInput = sc.nextLine();
        if (!ageInput.trim().isEmpty()) {
            currentUser.setAge(Integer.parseInt(ageInput));
        }
        
        System.out.print("Enter new phone (current: " + currentUser.getPhoneNumber() + "): ");
        String newPhone = sc.nextLine();
        if (!newPhone.trim().isEmpty()) {
            currentUser.setPhoneNumber(newPhone);
        }
        
        System.out.println("Profile updated successfully!");
    }

    public void viewAllUsers() {
        if (!AuthService.currentUser.getRole().equals(UserRole.ADMIN)) {
            System.out.println("Access denied!");
            return;
        }

        System.out.println("\n=== ALL USERS ===");
        for (User u : AuthService.users) {
            System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Role: " + u.getRole() + ", Active: " + u.isActive());
        }
    }
}
