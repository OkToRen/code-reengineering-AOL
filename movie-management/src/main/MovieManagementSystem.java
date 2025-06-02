package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import model.Movie;
import model.User;

public class MovieManagementSystem {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Movie> movies = new ArrayList<>();
    public static User currentUser = null;
    public static Scanner sc = new Scanner(System.in);
    public static int userIdCounter = 1;
    public static int movieIdCounter = 1;
    
    public static void main(String[] args) {
        System.out.println("=== MOVIE MANAGEMENT SYSTEM ===");
        
        User admin = new User();
        admin.id = userIdCounter++;
        admin.name = "admin";
        admin.password = "admin123";
        admin.email = "admin@movie.com";
        admin.age = 30;
        admin.phoneNumber = "1234567890";
        admin.isActive = true;
        admin.role = "ADMIN";
        admin.registrationDate = new Date(System.currentTimeMillis());
        users.add(admin);
        
        Movie m1 = new Movie();
        m1.id = movieIdCounter++;
        m1.name = "The Shawshank Redemption";
        m1.description = "Two imprisoned men bond over a number of years";
        m1.releaseDate = "1994-09-23";
        m1.genre = "Drama";
        m1.duration = 142;
        m1.rating = 9.3;
        m1.director = "Frank Darabont";
        m1.budget = 25000000;
        m1.boxOffice = 16000000;
        m1.isAvailable = true;
        movies.add(m1);
        
        Movie m2 = new Movie();
        m2.id = movieIdCounter++;
        m2.name = "The Godfather";
        m2.description = "The aging patriarch of an organized crime dynasty";
        m2.releaseDate = "1972-03-24";
        m2.genre = "Crime";
        m2.duration = 175;
        m2.rating = 9.2;
        m2.director = "Francis Ford Coppola";
        m2.budget = 6000000;
        m2.boxOffice = 245000000;
        m2.isAvailable = true;
        movies.add(m2);
        
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }
    
    public static void showLoginMenu() {
        System.out.println("\n=== LOGIN MENU ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline
        
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
    
    public static void registerUser() {
        System.out.println("\n=== USER REGISTRATION ===");
        User newUser = new User();
        
        newUser.id = userIdCounter++;
        
        System.out.print("Enter name: ");
        newUser.name = sc.nextLine();
        
        System.out.print("Enter password: ");
        newUser.password = sc.nextLine();
        
        System.out.print("Enter email: ");
        newUser.email = sc.nextLine();
        
        System.out.print("Enter age: ");
        newUser.age = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter phone number: ");
        newUser.phoneNumber = sc.nextLine();
        
        newUser.role = "USER";
        newUser.isActive = true;
        newUser.registrationDate = new Date(System.currentTimeMillis());
        
        users.add(newUser);
        System.out.println("Registration successful! User ID: " + newUser.id);
    }
    
    public static void loginUser() {
        System.out.println("\n=== USER LOGIN ===");
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        for (User u : users) {
            if (u.name.equals(name) && u.password.equals(password)) {
                if (u.isActive) {
                    currentUser = u;
                    System.out.println("Login successful! Welcome, " + u.name);
                    return;
                } else {
                    System.out.println("Account is inactive!");
                    return;
                }
            }
        }
        System.out.println("Invalid credentials!");
    }
    
    public static void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("Logged in as: " + currentUser.name + " (" + currentUser.role + ")");
        System.out.println("1. View All Movies");
        System.out.println("2. Add Movie");
        System.out.println("3. Update Movie");
        System.out.println("4. Delete Movie");
        System.out.println("5. Search Movies");
        System.out.println("6. View User Profile");
        System.out.println("7. Update Profile");
        System.out.println("8. View All Users (Admin Only)");
        System.out.println("9. Logout");
        System.out.print("Choose option: ");
        
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                viewAllMovies();
                break;
            case 2:
                addMovie();
                break;
            case 3:
                updateMovie();
                break;
            case 4:
                deleteMovie();
                break;
            case 5:
                searchMovies();
                break;
            case 6:
                viewProfile();
                break;
            case 7:
                updateProfile();
                break;
            case 8:
                if (currentUser.role.equals("ADMIN")) {
                    viewAllUsers();
                } else {
                    System.out.println("Access denied!");
                }
                break;
            case 9:
                currentUser = null;
                System.out.println("Logged out successfully!");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    public static void viewAllMovies() {
        System.out.println("\n=== ALL MOVIES ===");
        if (movies.isEmpty()) {
            System.out.println("No movies available!");
            return;
        }
        
        for (Movie m : movies) {
            System.out.println("ID: " + m.id);
            System.out.println("Name: " + m.name);
            System.out.println("Description: " + m.description);
            System.out.println("Release Date: " + m.releaseDate);
            System.out.println("Genre: " + m.genre);
            System.out.println("Duration: " + m.duration + " minutes");
            System.out.println("Rating: " + m.rating + "/10");
            System.out.println("Director: " + m.director);
            System.out.println("Budget: $" + m.budget);
            System.out.println("Box Office: $" + m.boxOffice);
            System.out.println("Available: " + (m.isAvailable ? "Yes" : "No"));
            System.out.println("-------------------------");
        }
    }
    
    public static void addMovie() {
        System.out.println("\n=== ADD MOVIE ===");
        Movie newMovie = new Movie();
        
        newMovie.id = movieIdCounter++;
        
        System.out.print("Enter movie name: ");
        newMovie.name = sc.nextLine();
        
        System.out.print("Enter description: ");
        newMovie.description = sc.nextLine();
        
        System.out.print("Enter release date (YYYY-MM-DD): ");
        newMovie.releaseDate = sc.nextLine();
        
        System.out.print("Enter genre: ");
        newMovie.genre = sc.nextLine();
        
        System.out.print("Enter duration (minutes): ");
        newMovie.duration = sc.nextInt();
        
        System.out.print("Enter rating (0-10): ");
        newMovie.rating = sc.nextDouble();
        sc.nextLine();
        
        System.out.print("Enter director: ");
        newMovie.director = sc.nextLine();
        
        System.out.print("Enter budget: ");
        newMovie.budget = sc.nextLong();
        
        System.out.print("Enter box office earnings: ");
        newMovie.boxOffice = sc.nextLong();
        sc.nextLine();
        
        newMovie.isAvailable = true;
        
        movies.add(newMovie);
        System.out.println("Movie added successfully! ID: " + newMovie.id);
    }
    
    public static void updateMovie() {
        System.out.println("\n=== UPDATE MOVIE ===");
        System.out.print("Enter movie ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Movie movieToUpdate = null;
        for (Movie m : movies) {
            if (m.id == id) {
                movieToUpdate = m;
                break;
            }
        }
        
        if (movieToUpdate == null) {
            System.out.println("Movie not found!");
            return;
        }
        
        System.out.println("Current movie details:");
        System.out.println("Name: " + movieToUpdate.name);
        System.out.println("Description: " + movieToUpdate.description);
        
        System.out.print("Enter new name (or press Enter to keep current): ");
        String newName = sc.nextLine();
        if (!newName.trim().isEmpty()) {
            movieToUpdate.name = newName;
        }
        
        System.out.print("Enter new description (or press Enter to keep current): ");
        String newDesc = sc.nextLine();
        if (!newDesc.trim().isEmpty()) {
            movieToUpdate.description = newDesc;
        }
        
        System.out.print("Enter new release date (or press Enter to keep current): ");
        String newDate = sc.nextLine();
        if (!newDate.trim().isEmpty()) {
            movieToUpdate.releaseDate = newDate;
        }
        
        System.out.print("Enter new genre (or press Enter to keep current): ");
        String newGenre = sc.nextLine();
        if (!newGenre.trim().isEmpty()) {
            movieToUpdate.genre = newGenre;
        }
        
        System.out.println("Movie updated successfully!");
    }
    
    public static void deleteMovie() {
        System.out.println("\n=== DELETE MOVIE ===");
        System.out.print("Enter movie ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Movie movieToDelete = null;
        for (Movie m : movies) {
            if (m.id == id) {
                movieToDelete = m;
                break;
            }
        }
        
        if (movieToDelete == null) {
            System.out.println("Movie not found!");
            return;
        }
        
        System.out.println("Are you sure you want to delete: " + movieToDelete.name + "? (y/n)");
        String confirm = sc.nextLine();
        if (confirm.toLowerCase().equals("y") || confirm.toLowerCase().equals("yes")) {
            movies.remove(movieToDelete);
            System.out.println("Movie deleted successfully!");
        } else {
            System.out.println("Deletion cancelled!");
        }
    }
    
    public static void searchMovies() {
        System.out.println("\n=== SEARCH MOVIES ===");
        System.out.println("1. Search by name");
        System.out.println("2. Search by genre");
        System.out.println("3. Search by director");
        System.out.print("Choose search type: ");
        
        int searchType = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter search term: ");
        String searchTerm = sc.nextLine().toLowerCase();
        
        ArrayList<Movie> results = new ArrayList<>();
        
        switch (searchType) {
            case 1:
                for (Movie m : movies) {
                    if (m.name.toLowerCase().contains(searchTerm)) {
                        results.add(m);
                    }
                }
                break;
            case 2:
                for (Movie m : movies) {
                    if (m.genre.toLowerCase().contains(searchTerm)) {
                        results.add(m);
                    }
                }
                break;
            case 3:
                for (Movie m : movies) {
                    if (m.director.toLowerCase().contains(searchTerm)) {
                        results.add(m);
                    }
                }
                break;
            default:
                System.out.println("Invalid search type!");
                return;
        }
        
        if (results.isEmpty()) {
            System.out.println("No movies found!");
        } else {
            System.out.println("Found " + results.size() + " movie(s):");
            for (Movie m : results) {
                System.out.println("ID: " + m.id + ", Name: " + m.name + ", Genre: " + m.genre);
            }
        }
    }
    
    public static void viewProfile() {
        System.out.println("\n=== USER PROFILE ===");
        System.out.println("ID: " + currentUser.id);
        System.out.println("Name: " + currentUser.name);
        System.out.println("Email: " + currentUser.email);
        System.out.println("Age: " + currentUser.age);
        System.out.println("Phone: " + currentUser.phoneNumber);
        System.out.println("Role: " + currentUser.role);
        System.out.println("Status: " + (currentUser.isActive ? "Active" : "Inactive"));
        System.out.println("Registration Date: " + currentUser.registrationDate);
    }
    
    public static void updateProfile() {
        System.out.println("\n=== UPDATE PROFILE ===");
        System.out.print("Enter new email (current: " + currentUser.email + "): ");
        String newEmail = sc.nextLine();
        if (!newEmail.trim().isEmpty()) {
            currentUser.email = newEmail;
        }
        
        System.out.print("Enter new age (current: " + currentUser.age + "): ");
        String ageInput = sc.nextLine();
        if (!ageInput.trim().isEmpty()) {
            currentUser.age = Integer.parseInt(ageInput);
        }
        
        System.out.print("Enter new phone (current: " + currentUser.phoneNumber + "): ");
        String newPhone = sc.nextLine();
        if (!newPhone.trim().isEmpty()) {
            currentUser.phoneNumber = newPhone;
        }
        
        System.out.println("Profile updated successfully!");
    }
    
    public static void viewAllUsers() {
        System.out.println("\n=== ALL USERS ===");
        for (User u : users) {
            System.out.println("ID: " + u.id + ", Name: " + u.name + ", Role: " + u.role + ", Active: " + u.isActive);
        }
    }
}

