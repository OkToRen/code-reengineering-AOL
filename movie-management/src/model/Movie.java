package model;

public class Movie {
    public int id;
    public String name;
    public String description;
    public String releaseDate;
    public String genre;
    public int duration; // in minutes
    public double rating;
    public String director;
    public long budget;
    public long boxOffice;
    public boolean isAvailable;
    
    public Movie() {
    }
    
    public void toggleAvailability() {
        isAvailable = !isAvailable;
    }
    
    public String getMovieInfo() {
        return name + " (" + releaseDate + ") - " + genre;
    }
}