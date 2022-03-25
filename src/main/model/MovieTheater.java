package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MovieTheater implements Writable {

    private LinkedList<Ticket> myTickets;
    private List<Movie> movies;
    private static double ticketPrice = 12.99;




    public MovieTheater() {
        myTickets = new LinkedList<Ticket>();
        movies = new ArrayList<>();
        movies.add(new Movie("The Batman", "9:30pm"));
        movies.add(new Movie("Encanto", "6:30pm"));
        movies.add(new Movie("Spider-man", "7:00pm"));
        movies.add(new Movie("Uncharted", "10:45pm"));
    }

    //MODIFIES: this
    //EFFECTS: adds given ticket to the list of tickets
    public void addTicket(Ticket t) {
        myTickets.add(t);
    }

    public void clearMovies() {
        movies.clear();
    }

    public void addMovie(Movie m) {
        movies.add(m);
    }

    public Movie findMovie(String title, String showtime) {
        for (Movie m: movies) {
            if (m.getTitle().equals(title) && m.getShowtime().equals(showtime)) {
                return m;
            }
        }
        return null;
    }

    //EFFECTS: returns number of tickets purchased
    public int getNumOfTickets() {
        return myTickets.size();
    }

    //EFFECTS: returns total cost for all tickets purchased
    public double getTotalPrice() {
        return (myTickets.size() * ticketPrice);
    }

    //EFFECTS: returns true if there is at least 1 ticket
    public boolean isPurchased() {
        return (myTickets.size() > 0);


    }

    // EFFECTS: returns an unmodifiable list of tickets
    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(myTickets);
    }

    // EFFECTS: returns an unmodifiable list of movies
    public List<Movie> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tickets", myTicketsToJson());
        json.put("payment", getTotalPrice());
        json.put("movies", moviesToJson());

        return json;
    }

    // EFFECTS: returns tickets as a JSON array
    private JSONArray myTicketsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ticket t : myTickets) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns movies as a JSON array
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : movies) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }



}
