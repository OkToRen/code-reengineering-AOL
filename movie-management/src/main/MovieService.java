package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import model.Movie;

public class MovieService {
    public static final ArrayList<Movie> movies = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private static int movieIdCounter = 1;

    public void viewAllMovies() {
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

    public void addMovie() {
    	System.out.println("\n=== ADD MOVIE ===");

        System.out.print("Enter movie name: ");
        String name = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter release date (YYYY-MM-DD): ");
        Date releaseDate = Date.valueOf(sc.nextLine());

        System.out.print("Enter genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter duration (minutes): ");
        int durationInMinutes = sc.nextInt();

        System.out.print("Enter rating (0-10): ");
        double rating = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter director: ");
        String director = sc.nextLine();

        System.out.print("Enter budget: ");
        long budget = sc.nextLong();

        System.out.print("Enter box office earnings: ");
        long boxOffice = sc.nextLong();
        sc.nextLine();

        Movie newMovie = new Movie(movieIdCounter++, name, description, releaseDate, genre,
                                durationInMinutes, rating, director, budget, boxOffice, true);

        movies.add(newMovie);
        System.out.println("Movie added successfully! ID: " + newMovie.getId());
    }

    public void updateMovie() {
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
            movieToUpdate.setReleaseDate(Date.valueOf(newDate));
        }
        
        System.out.print("Enter new genre (or press Enter to keep current): ");
        String newGenre = sc.nextLine();
        if (!newGenre.trim().isEmpty()) {
            movieToUpdate.setGenre(newGenre);
        }
        
        System.out.println("Movie updated successfully!");
    }

    public void deleteMovie() {
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
