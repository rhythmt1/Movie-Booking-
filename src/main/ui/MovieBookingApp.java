package ui;

import model.Movie;
import model.MyTickets;
import model.Ticket;
import java.util.Scanner;

public class MovieBookingApp {
    private Movie movie;
    private Ticket ticket;
    private Scanner input;
    private MyTickets tickets;

    public MovieBookingApp() {
        runBooking();
    }

    private void runBooking() {
        String command = null;
        tickets = new MyTickets();

        input = new Scanner(System.in);
        chooseMovie();
        command = input.next();
        processCommand(command);
        ticket = new Ticket(movie);
        selectSeat(command);
        addTickets(command);
        payment();
        System.out.println(tickets.getNumOfTickets() + " tickets have been purchased");
        System.out.println("Thank you and enjoy your movie!");
        rate();
        System.out.println("Thank you and enjoy your day!");


    }

    private void chooseMovie() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 - Encanto at 6:30pm");
        System.out.println("\t2 - The Batman at 9:30pm");
        System.out.println("\t3 - Spider-man at 7:00pm");
        System.out.println("\t4 - Uncharted at 10:45pm");

    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            book1();
        } else if (command.equals("2")) {
            book2();
        } else if (command.equals("3")) {
            book3();
        } else if (command.equals("4")) {
            book4();
        } else {
            System.out.println("Invalid entry");
            runBooking();
        }

    }

    private void book1() {
        movie = new Movie("Encanto", "6:30pm");
        System.out.println(movie.getTitle() + " is selected for " + movie.getShowtime());
        tickets.addTicket(ticket);
        System.out.println("This movie is rated " + movie.getRating());


    }

    private void book2() {
        movie = new Movie("The Batman", "9:30pm");
        System.out.println(movie.getTitle() + " is selected for " + movie.getShowtime());
        tickets.addTicket(ticket);
        System.out.println("This movie is rated " + movie.getRating());

    }

    private void book3() {
        movie = new Movie("Spider-man", "7:00pm");
        System.out.println(movie.getTitle() + " is selected for " + movie.getShowtime());
        tickets.addTicket(ticket);
        System.out.println("This movie is rated " + movie.getRating());
    }

    private void book4() {
        movie = new Movie("Uncharted", "10:45pm");
        System.out.println(movie.getTitle() + " is selected for " + movie.getShowtime());
        tickets.addTicket(ticket);
        System.out.println("This movie is rated " + movie.getRating());
    }

    private void addTickets(String command) {
        System.out.println("Would you like to add tickets?");
        System.out.println("\nSelect from:");
        System.out.println("\t - yes");
        System.out.println("\t - no");
        command = input.next();

        if (command.equals("yes")) {
            runBooking();
            tickets.addTicket(ticket);


        } else if (command.equals("no")) {
            System.out.println("Have a nice day!");
        } else {
            System.out.println("Invalid entry");
        }
    }

    private void selectSeat(String command) {
        System.out.println("please select a seat from 1 to 300");
        command = input.next();

        System.out.println("seat " + command + " as been booked for you");
    }

    private void payment() {

        System.out.println("Your payment is $" + tickets.getTotalPrice());
    }

    private void rate() {

        System.out.println("Please rate the movie");

        double command = input.nextDouble();
        movie.giveRating(command);
    }















}
