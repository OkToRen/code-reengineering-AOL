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
        admin.setId(userIdCounter++);
        admin.setName("admin");
        admin.setPassword("admin123");
        admin.setEmail("admin@movie.com");
        admin.setAge(30);
        admin.setPhoneNumber("1234567890");
        admin.setActive(true);
        admin.setRole("ADMIN");
        admin.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.add(admin);
        
        Movie m1 = new Movie();
        m1.setId(movieIdCounter++);
        m1.setName("The Shawshank Redemption");
        m1.setDescription("Two imprisoned men bond over a number of years");
        m1.setReleaseDate("1994-09-23");
        m1.setGenre("Drama");
        m1.setDurationInMinutes(142);
        m1.setRating(9.3);
        m1.setDirector("Frank Darabont");
        m1.setBudget(25000000);
        m1.setBoxOffice(16000000);
        m1.setAvailable(true);
        movies.add(m1);
        
        Movie m2 = new Movie();
        m2.setId(movieIdCounter++);
        m2.setName("The Godfather");
        m2.setDescription("The aging patriarch of an organized crime dynasty");
        m2.setReleaseDate("1972-03-24");
        m2.setGenre("Crime");
        m2.setDurationInMinutes(175);
        m2.setRating(9.2);
        m2.setDirector("Francis Ford Coppola");
        m2.setBudget(6000000);
        m2.setBoxOffice(245000000);
        m2.setAvailable(true);
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
    
    public static void registerUser() {
        System.out.println("\n=== USER REGISTRATION ===");
        User newUser = new User();
        
        newUser.setId(userIdCounter++);
        
        System.out.print("Enter name: ");
        newUser.setName(sc.nextLine());
        
        System.out.print("Enter password: ");
        newUser.setPassword(sc.nextLine());
        
        System.out.print("Enter email: ");
        newUser.setEmail(sc.nextLine());
        
        System.out.print("Enter age: ");
        newUser.setAge(sc.nextInt());
        sc.nextLine();
        
        System.out.print("Enter phone number: ");
        newUser.setPhoneNumber(sc.nextLine());
        
        newUser.setRole("USER");
        newUser.setActive(true);
        newUser.setRegistrationDate(new Date(System.currentTimeMillis()));
        
        users.add(newUser);
        System.out.println("Registration successful! User ID: " + newUser.getId());
    }
    
    public static void loginUser() {
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
    
    public static void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("Logged in as: " + currentUser.getName() + " (" + currentUser.getRole() + ")");
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
                if (currentUser.getRole().equals("ADMIN")) {
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
            System.out.println("ID: " + m.getId());
            System.out.println("Name: " + m.getName());
            System.out.println("Description: " + m.getDescription());
            System.out.println("Release Date: " + m.getReleaseDate());
            System.out.println("Genre: " + m.getGenre());
            System.out.println("Duration: " + m.getDurationInMinutes() + " minutes");
            System.out.println("Rating: " + m.getRating() + "/10");
            System.out.println("Director: " + m.getDirector());
            System.out.println("Budget: $" + m.getBudget());
            System.out.println("Box Office: $" + m.getBoxOffice());
            System.out.println("Available: " + (m.isAvailable() ? "Yes" : "No"));
            System.out.println("-------------------------");
        }
    }
    
    public static void addMovie() {
        System.out.println("\n=== ADD MOVIE ===");
        Movie newMovie = new Movie();
        
        newMovie.setId(movieIdCounter++);
        
        System.out.print("Enter movie name: ");
        newMovie.setName(sc.nextLine());
        
        System.out.print("Enter description: ");
        newMovie.setDescription(sc.nextLine());
        
        System.out.print("Enter release date (YYYY-MM-DD): ");
        newMovie.setReleaseDate(sc.nextLine());
        
        System.out.print("Enter genre: ");
        newMovie.setGenre(sc.nextLine());
        
        System.out.print("Enter duration (minutes): ");
        newMovie.setDurationInMinutes(sc.nextInt());
        
        System.out.print("Enter rating (0-10): ");
        newMovie.setRating(sc.nextDouble());
        sc.nextLine();
        
        System.out.print("Enter director: ");
        newMovie.setDirector(sc.nextLine());
        
        System.out.print("Enter budget: ");
        newMovie.setBudget(sc.nextLong());
        
        System.out.print("Enter box office earnings: ");
        newMovie.setBoxOffice(sc.nextLong());
        sc.nextLine();
        
        newMovie.setAvailable(true);
        
        movies.add(newMovie);
        System.out.println("Movie added successfully! ID: " + newMovie.getId());
    }
    
    public static void updateMovie() {
        System.out.println("\n=== UPDATE MOVIE ===");
        System.out.print("Enter movie ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        Movie movieToUpdate = null;
        for (Movie m : movies) {
            if (m.getId() == id) {
                movieToUpdate = m;
                break;
            }
        }
        
        if (movieToUpdate == null) {
            System.out.println("Movie not found!");
            return;
        }
        
        System.out.println("Current movie details:");
        System.out.println("Name: " + movieToUpdate.getName());
        System.out.println("Description: " + movieToUpdate.getDescription());
        
        System.out.print("Enter new name (or press Enter to keep current): ");
        String newName = sc.nextLine();
        if (!newName.trim().isEmpty()) {
            movieToUpdate.setName(newName);
        }
        
        System.out.print("Enter new description (or press Enter to keep current): ");
        String newDesc = sc.nextLine();
        if (!newDesc.trim().isEmpty()) {
            movieToUpdate.setDescription(newDesc);
        }
        
        System.out.print("Enter new release date (or press Enter to keep current): ");
        String newDate = sc.nextLine();
        if (!newDate.trim().isEmpty()) {
            movieToUpdate.setReleaseDate(newDate);
        }
        
        System.out.print("Enter new genre (or press Enter to keep current): ");
        String newGenre = sc.nextLine();
        if (!newGenre.trim().isEmpty()) {
            movieToUpdate.setGenre(newGenre);
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
            if (m.getId() == id) {
                movieToDelete = m;
                break;
            }
        }
        
        if (movieToDelete == null) {
            System.out.println("Movie not found!");
            return;
        }
        
        System.out.println("Are you sure you want to delete: " + movieToDelete.getName() + "? (y/n)");
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
                    if (m.getName().toLowerCase().contains(searchTerm)) {
                        results.add(m);
                    }
                }
                break;
            case 2:
                for (Movie m : movies) {
                    if (m.getGenre().toLowerCase().contains(searchTerm)) {
                        results.add(m);
                    }
                }
                break;
            case 3:
                for (Movie m : movies) {
                    if (m.getDirector().toLowerCase().contains(searchTerm)) {
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
                System.out.println("ID: " + m.getId() + ", Name: " + m.getName() + ", Genre: " + m.getGenre());
            }
        }
    }
    
    public static void viewProfile() {
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
    
    public static void updateProfile() {
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
    
    public static void viewAllUsers() {
        System.out.println("\n=== ALL USERS ===");
        for (User u : users) {
            System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Role: " + u.getRole() + ", Active: " + u.isActive());
        }
    }
}

