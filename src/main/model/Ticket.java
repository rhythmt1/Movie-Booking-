package model;

public class Ticket {
    private static final double price = 12.99;

    private int seatNum;
    private String title;
    private Movie movie;
    private String showtime;

    //EFFECTS: a ticket for a given movie
    public Ticket(Movie movie) {
        this.movie = movie;
        seatNum = 0;
        title = movie.getTitle();
        showtime = movie.getShowtime();

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

    //EFFECTS: returns the showtime on the ticket
    public String getShowtime() {
        return showtime;
    }

    //EFFECTS: returns the movie title on the ticket
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns the price of a ticket
    public double getPrice() {
        return price;
    }

}






