package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import helper.ConsoleHelper;
import model.Movie;

public class MovieService {
    public static final ArrayList<Movie> movies = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private static int movieIdCounter = 1;

    public void viewAllMovies() {
    	ConsoleHelper.printHeader("ALL MOVIES");
        if (movies.isEmpty()) {
            ConsoleHelper.print("No movies available!");
            return;
        }
        
        for (Movie m : movies) {
            ConsoleHelper.print("ID: " + m.getId());
            ConsoleHelper.print("Name: " + m.getName());
            ConsoleHelper.print("Description: " + m.getDescription());
            ConsoleHelper.print("Release Date: " + m.getReleaseDate());
            ConsoleHelper.print("Genre: " + m.getGenre());
            ConsoleHelper.print("Duration: " + m.getDurationInMinutes() + " minutes");
            ConsoleHelper.print("Rating: " + m.getRating() + "/10");
            ConsoleHelper.print("Director: " + m.getDirector());
            ConsoleHelper.print("Budget: $" + m.getBudget());
            ConsoleHelper.print("Box Office: $" + m.getBoxOffice());
            ConsoleHelper.print("Available: " + (m.isAvailable() ? "Yes" : "No"));
            ConsoleHelper.print("-------------------------");
        }
    }

    public void addMovie() {
    	ConsoleHelper.printHeader("ADD MOVIE");

        ConsoleHelper.printInline("Enter movie name: ");
        String name = sc.nextLine();

        ConsoleHelper.printInline("Enter description: ");
        String description = sc.nextLine();

        ConsoleHelper.printInline("Enter release date (YYYY-MM-DD): ");
        Date releaseDate = Date.valueOf(sc.nextLine());

        ConsoleHelper.printInline("Enter genre: ");
        String genre = sc.nextLine();

        ConsoleHelper.printInline("Enter duration (minutes): ");
        int durationInMinutes = sc.nextInt();

        ConsoleHelper.printInline("Enter rating (0-10): ");
        double rating = sc.nextDouble();
        sc.nextLine();

        ConsoleHelper.printInline("Enter director: ");
        String director = sc.nextLine();

        ConsoleHelper.printInline("Enter budget: ");
        long budget = sc.nextLong();

        ConsoleHelper.printInline("Enter box office earnings: ");
        long boxOffice = sc.nextLong();
        sc.nextLine();

        Movie newMovie = new Movie(movieIdCounter++, name, description, releaseDate, genre,
                                durationInMinutes, rating, director, budget, boxOffice, true);

        movies.add(newMovie);
        ConsoleHelper.print("Movie added successfully! ID: " + newMovie.getId());
    }

    public void updateMovie() {
    	ConsoleHelper.printHeader("UPDATE MOVIE");
        ConsoleHelper.printInline("Enter movie ID to update: ");
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
            ConsoleHelper.print("Movie not found!");
            return;
        }
        
        ConsoleHelper.print("Current movie details:");
        ConsoleHelper.print("Name: " + movieToUpdate.getName());
        ConsoleHelper.print("Description: " + movieToUpdate.getDescription());
        
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
            movieToUpdate.setReleaseDate(Date.valueOf(newDate));
        }
        
        System.out.print("Enter new genre (or press Enter to keep current): ");
        String newGenre = sc.nextLine();
        if (!newGenre.trim().isEmpty()) {
            movieToUpdate.setGenre(newGenre);
        }
        
        ConsoleHelper.print("Movie updated successfully!");
    }

    public void deleteMovie() {
    	ConsoleHelper.printHeader("\n=== DELETE MOVIE ===");
        ConsoleHelper.printInline("Enter movie ID to delete: ");
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
            ConsoleHelper.printError("Movie not found!");
            return;
        }
        
        ConsoleHelper.print("Are you sure you want to delete: " + movieToDelete.getName() + "? (y/n)");
        String confirm = sc.nextLine();
        if (confirm.toLowerCase().equals("y") || confirm.toLowerCase().equals("yes")) {
            movies.remove(movieToDelete);
            ConsoleHelper.print("Movie deleted successfully!");
        } else {
            ConsoleHelper.print("Deletion cancelled!");
        }
    }

    public void seedMovies() {
    	Movie m1 = new Movie(
                movieIdCounter++,
                "The Shawshank Redemption",
                "Two imprisoned men bond over a number of years",
                Date.valueOf("1994-09-23"),
                "Drama",
                142,
                9.3,
                "Frank Darabont",
                25000000,
                16000000,
                true
            );
            movies.add(m1);

            Movie m2 = new Movie(
                movieIdCounter++,
                "The Godfather",
                "The aging patriarch of an organized crime dynasty",
                Date.valueOf("1972-03-24"),
                "Crime",
                175,
                9.2,
                "Francis Ford Coppola",
                6000000,
                245000000,
                true
            );
            movies.add(m2);
    }

    public static int getNextMovieId() {
        return ++movieIdCounter;
    }
}
