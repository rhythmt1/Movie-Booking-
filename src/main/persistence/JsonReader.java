package persistence;

import model.Movie;
import model.MyTickets;
import model.Ticket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyTickets read() throws IOException {
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

    // EFFECTS: parses workroom from JSON object and returns it
    private MyTickets parseMyTickets(JSONObject jsonObject) {
        MyTickets mt = new MyTickets();
        addTickets(mt, jsonObject);
        return mt;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addTickets(MyTickets mt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tickets");
        for (Object json : jsonArray) {
            JSONObject nextTicket = (JSONObject) json;
            addTicket(mt, nextTicket);
        }
    }

    // MODIFIES: mt
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addTicket(MyTickets mt, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String showtime = jsonObject.getString("showtime");
        Movie movie = new Movie(title, showtime);
        Ticket t = new Ticket(movie);
        mt.addTicket(t);
    }
}
