package model;

import java.sql.Date;

public class Movie {
	private int id;
	private String name;
	private String description;
	private Date releaseDate;
	private String genre;
	private int durationInMinutes;
	private double rating;
	private String director;
	private long budget;
    private long boxOffice;
    private boolean isAvailable;
    
	public Movie(int id, String name, String description, Date releaseDate, String genre,
                 int durationInMinutes, double rating, String director,
                 long budget, long boxOffice, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
        this.rating = rating;
        this.director = director;
        this.budget = budget;
        this.boxOffice = boxOffice;
        this.isAvailable = isAvailable;
    }

    public void toggleAvailability() {
        isAvailable = !isAvailable;
    }
    
    public String getMovieInfo() {
        return name + " (" + releaseDate + ") - " + genre;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public long getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(long boxOffice) {
		this.boxOffice = boxOffice;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
    
    
}