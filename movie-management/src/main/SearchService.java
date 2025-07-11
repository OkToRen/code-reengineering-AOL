package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import helper.ConsoleHelper;
import model.Movie;

public class SearchService {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<Integer, Function<String, ArrayList<Movie>>> searchStrategies = new HashMap<>();

    static {
        searchStrategies.put(1, SearchService::searchByName);
        searchStrategies.put(2, SearchService::searchByGenre);
        searchStrategies.put(3, SearchService::searchByDirector);
    }

    public static void searchMovies() {
        ConsoleHelper.print("1. Search by name\n2. Genre\n3. Director");
        int type = sc.nextInt(); sc.nextLine();
        ConsoleHelper.printInline("Enter term: ");
        String term = sc.nextLine().toLowerCase();

        var strategy = searchStrategies.get(type);
        if (strategy == null) {
            ConsoleHelper.printError("Invalid type");
            return;
        }

        var results = strategy.apply(term);
        if (results.isEmpty()) {
            ConsoleHelper.print("No results.");
        } else {
            for (Movie m : results) {
                ConsoleHelper.print("ID: " + m.getId() + ", Name: " + m.getName());
            }
        }
    }

    private static ArrayList<Movie> searchByName(String term) {
        return MovieService.movies.stream()
            .filter(m -> m.movieNameContains(term))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Movie> searchByGenre(String term) {
        return MovieService.movies.stream()
            .filter(m -> m.getGenre().toLowerCase().contains(term))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Movie> searchByDirector(String term) {
        return MovieService.movies.stream()
            .filter(m -> m.movieDirectorContains(term))
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
