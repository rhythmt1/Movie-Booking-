package model;

import java.util.LinkedList;

public class MyTickets {

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

}
