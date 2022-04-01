package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class JTableGUI implements LogPrinter {
    private static final String JSON_STORE = "./data/tickets.json";
    JFrame frame;
    JTable table;
    JTextField textMovie;
    JTextField textSeatNum;
    JButton addButton;
    JButton dltButton;
    DefaultTableModel model;
    Object[] row;
    JScrollPane pane;
    JLabel movieLabel;
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
        textSeatNum = new JTextField();
        addLabels();


        addButton = new JButton("Add Ticket");
        dltButton = new JButton("Remove Ticket");
        textMovie.setBounds(130, 220, 110, 25);
        textSeatNum.setBounds(130, 250, 110, 25);
        addButton.setBounds(250, 220, 150, 25);
        dltButton.setBounds(250, 265, 150, 25);
        pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        setUp();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    //EFFECTS: sets up frame and action listener
    @SuppressWarnings("methodlength")
    public void setUp() {


        frame.setLayout(null);
        frame.add(pane);
        frame.add(textMovie);
        frame.add(textSeatNum);

        frame.add(addButton);
        frame.add(dltButton);
        row = new Object[3];
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = textMovie.getText();
                row[2] = textSeatNum.getText();

                if (encanto.getTitle().equals(textMovie.getText())) {
                    row[1] = encanto.getShowtime();
                    model.addRow(row);
                    ticket = new Ticket(encanto, Integer.parseInt(textSeatNum.getText()));
                    tickets.addTicket(ticket);
                } else if (batman.getTitle().equals(textMovie.getText())) {
                    row[1] = batman.getShowtime();
                    model.addRow(row);
                    ticket = new Ticket(batman, Integer.parseInt(textSeatNum.getText()));
                    tickets.addTicket(ticket);
                } else if (spiderman.getTitle().equals(textMovie.getText())) {
                    row[1] = spiderman.getShowtime();
                    model.addRow(row);
                    ticket = new Ticket(spiderman, Integer.parseInt(textSeatNum.getText()));
                    tickets.addTicket(ticket);
                } else if (uncharted.getTitle().equals(textMovie.getText())) {
                    row[1] = uncharted.getShowtime();
                    model.addRow(row);
                    ticket = new Ticket(uncharted, Integer.parseInt(textSeatNum.getText()));
                    tickets.addTicket(ticket);
                }
            }
        });
        removeListener();
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    printLog(EventLog.getInstance());
                } catch (LogException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }

        });

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
                    tickets.removeTicket(i);
                } else {
                    System.out.println("Delete Error");
                }
            }
        });
    }

    //EFFECTS: adds labels to frame
    public void addLabels() {
        movieLabel = new JLabel("Enter Movie");
        seatNumLabel = new JLabel("Enter Seat #");
        movieLabel.setBounds(20, 220, 110, 25);
        seatNumLabel.setBounds(20, 250, 110, 25);
        frame.add(movieLabel);
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
    @SuppressWarnings("methodlength")
    public void loadTable() {

        try {
            tickets = jsonReader.read();
            allMovies = jsonReader.read2();
            batman = tickets.getMovies().get(0);
            encanto = tickets.getMovies().get(1);
            spiderman = tickets.getMovies().get(2);
            uncharted = tickets.getMovies().get(3);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] columns = {"Movie Title", "Movie Showtime", "Seat Number"};
            String[][] data = {
                    {getMovieTitle(0),
                            getMovieShowtime(0),
                            getSeatNum(0)}, {getMovieTitle(1),
                    getMovieShowtime(1),
                    getSeatNum(1)}, {getMovieTitle(2),
                    getMovieShowtime(2),
                    getSeatNum(2)}, {getMovieTitle(3),
                    getMovieShowtime(3),
                    getSeatNum(3)}
            };

            model.setDataVector(data, columns);

            System.out.println("Loaded tickets from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    private String getMovieTitle(int index) {
        if (tickets.getTickets().get(index) == null) {
            return "empty";
        } else {
            return tickets.getTickets().get(index).getMovie().getTitle();
        }
    }

    private String getMovieShowtime(int index) {
        if (tickets.getTickets().get(index) == null) {
            return "empty";
        } else {
            return tickets.getTickets().get(index).getMovie().getShowtime();
        }
    }

    private String getSeatNum(int index) {
        if (tickets.getTickets().get(index) == null) {
            return "empty";
        } else {
            return String.valueOf(tickets.getTickets().get(index).getSeatNum());
        }
    }



    public static void main(String[] args) {
        new JTableGUI();
    }

    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event event : el) {
            System.out.println(event.getDescription() + " on " + event.getDate());
        }
    }
}
