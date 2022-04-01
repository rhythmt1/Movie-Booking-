package model;

import org.json.JSONObject;
import persistence.Writable;

public class Ticket implements Writable {
    private int seatNum;
    private Movie movie;

    //EFFECTS: a ticket for a given movie
    public Ticket(Movie movie) {
        this.movie = movie;
        seatNum = 0;
    }

    public Ticket(Movie movie, int seatNum) {
        this.movie = movie;
        this.seatNum = seatNum;
    }

    //REQUIRES: seatNum >= 1 and <= 300
    //MODIFIES: this
    //EFFECTS: assigns the given seat number
    public void selectSeat(int seatNum) {
        this.seatNum = seatNum;

    }

    //EFFECTS: returns seat number on the ticket
    public int getSeatNum() {
        return seatNum;
    }


    //EFFECTS: returns the movie title on the ticket
    public Movie getMovie() {
        return movie;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movie title", movie.getTitle());
        json.put("movie showtime", movie.getShowtime());
        json.put("seat number", seatNum);
        return json;
    }

}






