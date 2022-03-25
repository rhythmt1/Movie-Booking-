package ui;

import model.Movie;
import model.MovieTheater;
import model.Ticket;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Movie Theater Application
public class MovieBookingApp {
    private static final String JSON_STORE = "./data/tickets.json";

    private Movie batman;
    private Movie encanto;
    private Movie uncharted;
    private Movie spiderman;
    private MovieTheater tickets;
    private List<Movie> allMovies;
    private Ticket ticket;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs MovieBookingApp
    public MovieBookingApp() {
        tickets = new MovieTheater();
        allMovies = tickets.getMovies();
        batman = allMovies.get(0);
        encanto = allMovies.get(1);
        spiderman = allMovies.get(2);
        uncharted = allMovies.get(3);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBooking();
    }

    //EFFECTS: runs MovieBookingApp
    private void runBooking() {
        String command = null;
        boolean keepGoing = true;

        input = new Scanner(System.in);
        while (keepGoing) {
            homePage();
            command = input.next();
            processHome(command);
            System.out.println("Thank you and enjoy your day!");



        }

    }

    //EFFECTS: prints user options for app
    private void homePage() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 - Choose a movie");
        System.out.println("\t2 - Rate Movie");
        System.out.println("\t3 - See Movie Ratings");
        System.out.println("\t4 - View Payment");
        System.out.println("\t5 - Save Data");
        System.out.println("\t6 - Load Data");

    }

    //EFFECTS: processes commands from homePage
    private void processHome(String command) {
        if (command.equals("1")) {
            watch(command);
        } else if (command.equals("2")) {
            rate(command);
        } else if (command.equals("3")) {
            seeRating(command);
        } else if (command.equals("4")) {
            payment();
        } else if (command.equals("5")) {
            saveTickets();
        } else if (command.equals("6")) {
            loadTickets();
        } else {
            System.out.println("Invalid entry");
            homePage();
        }

    }

    //EFFECTS: prints available movie options
    private void chooseMovie() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 - Encanto at 6:30pm");
        System.out.println("\t2 - The Batman at 9:30pm");
        System.out.println("\t3 - Spider-man at 7:00pm");
        System.out.println("\t4 - Uncharted at 10:45pm");

    }

    //EFFECTS: processes commands from chooseMovie
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

    //MODIFIES: ticket
    //EFFECTS: books Ticket for Encanto movie
    private void book1() {
        ticket = new Ticket(encanto);

        System.out.println(encanto.getTitle() + " is selected for " + encanto.getShowtime());
        tickets.addTicket(ticket);

    }

    //MODIFIES: ticket
    //EFFECTS: books Ticket for The Batman movie
    private void book2() {
        ticket = new Ticket(batman);

        System.out.println(batman.getTitle() + " is selected for " + batman.getShowtime());
        tickets.addTicket(ticket);

    }

    //MODIFIES: ticket
    //EFFECTS: books Ticket for Spider-man movie
    private void book3() {
        ticket = new Ticket(spiderman);

        System.out.println(spiderman.getTitle() + " is selected for " + spiderman.getShowtime());
        tickets.addTicket(ticket);
    }

    //MODIFIES: ticket
    //EFFECTS: books Ticket for Uncharted movie
    private void book4() {
        ticket = new Ticket(uncharted);

        System.out.println(uncharted.getTitle() + " is selected for " + uncharted.getShowtime());
        tickets.addTicket(ticket);
    }

    //MODIFIES: ticket
    //EFFECTS: allows the user to select seats
    private void selectSeat(String command) {
        System.out.println("please select a seat from 1 to 300");
        command = input.next();
        int seat = Integer.parseInt(command);
        ticket.selectSeat(seat);

        System.out.println("seat " + command + " as been booked for you");
        payment();
    }

    //EFFECTS: prints payment value for tickets
    private void payment() {

        System.out.println("Your payment is $" + tickets.getTotalPrice());

    }

    //MODIFIES: movie
    //EFFECTS: adds given rating to selected movie
    private void rate(String command) {
        System.out.println("\nChoose movie to rate");
        System.out.println("\t1 - Encanto");
        System.out.println("\t2 - The Batman");
        System.out.println("\t3 - Spider-man");
        System.out.println("\t4 - Uncharted");
        command = input.next();

        if (command.equals("1")) {
            System.out.println("Please rate Encanto from 1-10");
            double command1 = input.nextDouble();
            encanto.giveRating(command1);
        } else if (command.equals("2")) {
            System.out.println("Please rate The Batman from 1-10");
            double command1 = input.nextDouble();
            batman.giveRating(command1);
        } else if (command.equals("3")) {
            System.out.println("Please rate Spider-man from 1-10");
            double command1 = input.nextDouble();
            spiderman.giveRating(command1);
        } else if (command.equals("4")) {
            System.out.println("Please rate Uncharted from 1-10");
            double command1 = input.nextDouble();
            uncharted.giveRating(command1);
        }
    }

    //EFFECTS: reruns chooseMovie
    private void watch(String command) {
        chooseMovie();
        command = input.next();
        processCommand(command);
    }

    //EFFECTS: prints the rating of selected movie
    private void seeRating(String command) {
        System.out.println("\nChoose movie to see rating for");
        System.out.println("\t1 - Encanto");
        System.out.println("\t2 - The Batman");
        System.out.println("\t3 - Spider-man");
        System.out.println("\t4 - Uncharted");
        command = input.next();

        if (command.equals("1")) {
            System.out.println("This movie is rated " + encanto.getRating());
        } else if (command.equals("2")) {
            System.out.println("This movie is rated " + batman.getRating());
        } else if (command.equals("3")) {
            System.out.println("This movie is rated " + spiderman.getRating());
        } else if (command.equals("4")) {
            System.out.println("This movie is rated " + uncharted.getRating());
        } else {
            System.out.println("Invalid entry");
        }

    }

    // EFFECTS: saves the tickets to file
    private void saveTickets() {
        try {
            jsonWriter.open();
            jsonWriter.write(tickets);
            jsonWriter.close();
            System.out.println("Saved tickets to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tickets from file
    private void loadTickets() {
        try {
            tickets = jsonReader.read();
            allMovies = jsonReader.read2();
            batman = tickets.getMovies().get(0);
            encanto = tickets.getMovies().get(1);
            spiderman = tickets.getMovies().get(2);
            uncharted = tickets.getMovies().get(3);



            System.out.println("Loaded tickets from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        new MovieBookingApp();
    }

}
