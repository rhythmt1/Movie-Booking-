package persistence;

import model.Movie;
import model.MovieTheater;
import model.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tickets from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieTheater read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMyTickets(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //MODIFIES: mt
    // EFFECTS: parses MyTickets from JSON object and returns it
    private MovieTheater parseMyTickets(JSONObject jsonObject) {
        MovieTheater mt = new MovieTheater();
        // read movies and add them to array
        mt.clearMovies();
        addMovies(mt, jsonObject);
        // read tickets, find corresponding movie, create relationship and add to array of tickets
        addTickets(mt, jsonObject);

        return mt;
    }

    // MODIFIES: mt
    // EFFECTS: parses tickets from JSON object and adds them to MyTickets
    private void addTickets(MovieTheater mt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tickets");
        for (Object json : jsonArray) {
            JSONObject nextTicket = (JSONObject) json;
            addTicket(mt, nextTicket);
        }
    }

    // MODIFIES: mt
    // EFFECTS: parses ticket from JSON object and adds it to MyTickets
    private void addTicket(MovieTheater mt, JSONObject jsonObject) {
        String title = jsonObject.getString("movie title");
        String showtime = jsonObject.getString("movie showtime");
        int seatNum = jsonObject.getInt("seat number");
        // seat number

        Movie m = mt.findMovie(title, showtime);
        if (m != null) {
            Ticket t = new Ticket(m, seatNum);
            mt.addTicket(t);
        }


    }

    // MODIFIES: mt
    // EFFECTS: parses movie from JSON object and adds it to MyTickets
    private void addMovie(MovieTheater mt, JSONObject jsonObject) {
        String title = jsonObject.getString("movie title");
        String showtime = jsonObject.getString("movie showtime");
        double rating = jsonObject.getDouble("movie rating");
        int numOfRatings = jsonObject.getInt("number of ratings");
        double total = jsonObject.getDouble("total ratings");

        Movie movie = new Movie(title, showtime, rating, numOfRatings, total);
        mt.addMovie(movie);
    }

    // MODIFIES: mt
    // EFFECTS: parses movies from JSON object and adds it to MyTickets
    private void addMovies(MovieTheater mt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(mt, nextMovie);
        }
    }

    // EFFECTS: parses MyTickets from JSON object and returns it
    private ArrayList<Movie> parseMovies(JSONObject jsonObject) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Batman", "9:30pm"));
        movies.add(new Movie("Encanto", "6:30pm"));
        movies.add(new Movie("Spider-man", "7:00pm"));
        movies.add(new Movie("Uncharted", "10:45pm"));
        return movies;

    }

    // EFFECTS: reads movies from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Movie> read2() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovies(jsonObject);

    }



}
