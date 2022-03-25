package model;

import org.json.JSONObject;
import persistence.Writable;

public class Movie implements Writable {
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

    public Movie(String title, String showtime, double rating, int numOfRatings, double total) {
        this.title = title;
        this.showtime = showtime;
        this.rating = rating;
        this.numOfRatings = numOfRatings;
        this.total = total;


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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movie title", title);
        json.put("movie showtime", showtime);
        json.put("movie rating", rating);
        json.put("number of ratings", numOfRatings);
        json.put("total ratings", total);

        return json;
    }
}
