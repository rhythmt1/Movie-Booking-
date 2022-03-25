package ui;

import model.Movie;
import model.MovieTheater;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//Main page for Movie Theater App
public class MainPageGUI implements ActionListener {
    private static final String JSON_STORE = "./data/tickets.json";
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JFrame frame;
    private JPanel panel;
    private JLabel image;
    private JFrame frame2;
    private Movie batman;
    private Movie encanto;
    private Movie uncharted;
    private Movie spiderman;
    private MovieTheater tickets;
    private List<Movie> allMovies;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;




    //EFFECTS: constructs main page
    @SuppressWarnings("methodlength")
    public MainPageGUI() {
        tickets = new MovieTheater();
        allMovies = tickets.getMovies();
        batman = allMovies.get(0);
        encanto = allMovies.get(1);
        spiderman = allMovies.get(2);
        uncharted = allMovies.get(3);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame();
        frame2 = new JFrame();
        panel = new JPanel();
        button1 = new JButton("Add or Remove A Ticket");
        button2 = new JButton("Save Tickets");
        button3 = new JButton("Load Tickets");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        image = new JLabel(new ImageIcon("./data/movie.jpeg"));
        image.setBounds(250, 15, 100, 100);
        image.setVisible(true);
        frame2.add(image);
        image.setSize(50, 50);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(500, 500);
        frame2.setVisible(true);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Booking App");


        frame.pack();
        frame.setVisible(true);


    }

    //EFFECTS: listens to button event and performs action
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == button1) {
            new JTableGUI();
            frame2.setVisible(false);

        } else if (o == button2) {
            saveTable();
        } else if (o == button3) {
            loadTickets();

        }
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
        new MainPageGUI();
    }
}
