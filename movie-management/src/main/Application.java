package main;

import java.util.Scanner;

public class Application {
    private final AuthService authService = new AuthService();
    private final MovieService movieService = new MovieService();
    private final UserService userService = new UserService();
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("=== MOVIE MANAGEMENT SYSTEM ===");
        authService.seedAdmin();
        movieService.seedMovies();

        while (true) {
            if (AuthService.currentUser == null) {
                authService.showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("Logged in as: " + AuthService.currentUser.getName() + " (" + AuthService.currentUser.getRole() + ")");
        System.out.println("1. View All Movies");
        System.out.println("2. Add Movie");
        System.out.println("3. Update Movie");
        System.out.println("4. Delete Movie");
        System.out.println("5. Search Movies");
        System.out.println("6. View Profile");
        System.out.println("7. Update Profile");
        System.out.println("8. View All Users (Admin Only)");
        System.out.println("9. Logout");
        System.out.print("Choose option: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 : movieService.viewAllMovies(); break;
            case 2 : movieService.addMovie();break;
            case 3 : movieService.updateMovie();break;
            case 4 : movieService.deleteMovie();break;
            case 5 : SearchService.searchMovies();break;
            case 6 : userService.viewProfile();break;
            case 7 : userService.updateProfile();break;
            case 8 : userService.viewAllUsers();break;
            case 9 : {
                AuthService.currentUser = null;
                System.out.println("Logged out successfully!");break;
            }
            default : System.out.println("Invalid choice!");break;
        }
    }
}
