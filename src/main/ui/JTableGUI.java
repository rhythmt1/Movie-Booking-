package ui;

import model.Movie;
import model.MovieTheater;
import model.Ticket;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class JTableGUI {
    private static final String JSON_STORE = "./data/tickets.json";
    JFrame frame;
    JTable table;
    JTextField textMovie;
    JTextField textShowtime;
    JTextField textSeatNum;
    JButton addButton;
    JButton dltButton;
    DefaultTableModel model;
    Object[] row;
    JScrollPane pane;
    JLabel movieLabel;
    JLabel showtimeLabel;
    JLabel seatNumLabel;
    private Movie batman;
    private Movie encanto;
    private Movie uncharted;
    private Movie spiderman;
    private MovieTheater tickets;
    private Ticket ticket;
    private List<Movie> allMovies;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    //Table for movie tickets
    @SuppressWarnings("methodlength")
    public JTableGUI() {
        frame = new JFrame();
        table = new JTable();
        tickets = new MovieTheater();
        allMovies = tickets.getMovies();
        batman = allMovies.get(0);
        encanto = allMovies.get(1);
        spiderman = allMovies.get(2);
        uncharted = allMovies.get(3);
        Object[] columns = {"Movie Title", "Movie Showtime", "Seat Number"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setRowHeight(30);
        textMovie = new JTextField();
        textShowtime = new JTextField();
        textSeatNum = new JTextField();
        addLabels();


        addButton = new JButton("Add Ticket");
        dltButton = new JButton("Remove Ticket");
        textMovie.setBounds(130, 220, 110, 25);
        textShowtime.setBounds(130, 250, 110, 25);
        textSeatNum.setBounds(130, 280, 110, 25);
        addButton.setBounds(250, 220, 150, 25);
        dltButton.setBounds(250, 265, 150, 25);
        pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        setUp();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        if (textMovie.getText() == encanto.getTitle()) {
            ticket = new Ticket(encanto);
            tickets.addTicket(ticket);
        } else if (textMovie.getText() == batman.getTitle()) {
            ticket = new Ticket(batman);
            tickets.addTicket(ticket);
        } else if (textMovie.getText() == spiderman.getTitle()) {
            ticket = new Ticket(spiderman);
            tickets.addTicket(ticket);
        } else if (textMovie.getText() == uncharted.getTitle()) {
            ticket = new Ticket(uncharted);
            tickets.addTicket(ticket);
        }
    }

    //EFFECTS: sets up frame and action listener
    public void setUp() {


        frame.setLayout(null);
        frame.add(pane);
        frame.add(textMovie);
        frame.add(textShowtime);
        frame.add(textSeatNum);

        frame.add(addButton);
        frame.add(dltButton);
        row = new Object[3];
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = textMovie.getText();
                row[1] = textShowtime.getText();
                row[2] = textSeatNum.getText();

                model.addRow(row);


            }
        });
        removeListener();
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Booking App");

        frame.setVisible(true);
    }

    //EFFECTS: sets up action listener for remove ticket button
    public void removeListener() {
        dltButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();

                if (i >= 0) {
                    model.removeRow(i);
                } else {
                    System.out.println("Delete Error");
                }
            }
        });
    }

    //EFFECTS: adds labels to frame
    public void addLabels() {
        movieLabel = new JLabel("Enter Movie");
        showtimeLabel = new JLabel("Enter Showtime");
        seatNumLabel = new JLabel("Enter Seat #");
        movieLabel.setBounds(20, 220, 110, 25);
        showtimeLabel.setBounds(20, 250, 110, 25);
        seatNumLabel.setBounds(20, 280, 110, 25);
        frame.add(movieLabel);
        frame.add(showtimeLabel);
        frame.add(seatNumLabel);
    }

    //saves the tickets to file
    public void saveTable() {
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
    public void loadTable() {
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
        new JTableGUI();
    }

}
