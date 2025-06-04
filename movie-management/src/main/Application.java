package main;

import java.util.Scanner;

import helper.ConsoleHelper;

public class Application {
    private final AuthService authService = new AuthService();
    private final MovieService movieService = new MovieService();
    private final UserService userService = new UserService();
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        ConsoleHelper.printHeader("MOVIE MANAGEMENT SYSTEM");
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
        ConsoleHelper.printHeader("MAIN MENU");
        ConsoleHelper.print("Logged in as: " + AuthService.currentUser.getName() + " (" + AuthService.currentUser.getRole() + ")");
        ConsoleHelper.print("1. View All Movies");
        ConsoleHelper.print("2. Add Movie");
        ConsoleHelper.print("3. Update Movie");
        ConsoleHelper.print("4. Delete Movie");
        ConsoleHelper.print("5. Search Movies");
        ConsoleHelper.print("6. View Profile");
        ConsoleHelper.print("7. Update Profile");
        ConsoleHelper.print("8. View All Users (Admin Only)");
        ConsoleHelper.print("9. Logout");
        ConsoleHelper.printInline("Choose option: ");

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
                ConsoleHelper.print("Logged out successfully!");break;
            }
            default : ConsoleHelper.printError("Invalid choice!");break;
        }
    }
}
