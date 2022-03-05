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
        homePage();
        command = input.next();
        processHome(command);
        ticket = new Ticket(movie);
        System.out.println("Thank you and enjoy your day!");
        homePage();
        command = input.next();
        processHome(command);


    }

    private void homePage() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 - Choose a movie");
        System.out.println("\t2 - Add more tickets");
        System.out.println("\t3 - Rate Movie");
        System.out.println("\t4 - See Movie Ratings");
        System.out.println("\t5 - View Payment");
        System.out.println("\t6 - Exit");





    }

    private void processHome(String command) {
        if (command.equals("1")) {
            watch(command);
        } else if (command.equals("2")) {
            addTickets(command);
        } else if (command.equals("3")) {
            rate();
        } else if (command.equals("4")) {
            movie.getRating();
        } else if (command.equals("6")) {
            System.out.println("Goodbye");
        } else if (command.equals("5")) {
            payment();
        } else {
            System.out.println("Invalid entry");
            homePage();
        }

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
        selectSeat(command);

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
            watch(command);



        } else if (command.equals("no")) {
            System.out.println("Have a nice day!");
        } else {
            System.out.println("Invalid entry");

        }
        System.out.println(tickets.getNumOfTickets() + " tickets have been purchased");
        homePage();
        command = input.next();
        processHome(command);

    }

    private void selectSeat(String command) {
        System.out.println("please select a seat from 1 to 300");
        command = input.next();

        System.out.println("seat " + command + " as been booked for you");
        payment();
    }

    private void payment() {

        System.out.println("Your payment is $" + tickets.getTotalPrice());

    }

    private void rate() {

        System.out.println("Please rate the movie from 1-10");

        double command = input.nextDouble();
        movie.giveRating(command);
        movie.getRating();
    }

    private void watch(String command) {
        chooseMovie();
        command = input.next();
        processCommand(command);
    }










}
