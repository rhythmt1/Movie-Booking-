package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyTickets implements Writable {

    private static LinkedList<Ticket> myTickets;
    private static double ticketPrice = 12.99;

    public MyTickets() {
        myTickets = new LinkedList<Ticket>();
    }

    //MODIFIES: this
    //EFFECTS: adds given ticket to the list of tickets
    public void addTicket(Ticket t) {
        myTickets.add(t);

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tickets", myTicketsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray myTicketsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ticket t : myTickets) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
