package model;

public class Movie {
    private String title;
    private String showtime;
    private double rating;
    private int numOfRatings;
    private double total;

    //EFFECTS: A movie with a title and its showtime.
    public Movie(String title, String showtime) {
        this.title = title;
        this.showtime = showtime;
        this.rating = 0;
        this.numOfRatings = 0;
        this.total = 0;


    }

    //REQUIRES: rating is >= 1 and <= 10
    //MODIFIES: this
    //EFFECTS: adds rating to the average rating of the movie
    public void giveRating(double rating) {
        numOfRatings++;
        total += rating;
        this.rating = total / numOfRatings;
        this.rating = (double) Math.round(this.rating * 10) / 10;

    }

    //EFFECTS: returns the average movie rating
    public double getRating() {
        return this.rating;
    }

    //EFFECTS: returns the title of the movie
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns the showtime for the movie
    public String getShowtime() {
        return showtime;
    }



}
