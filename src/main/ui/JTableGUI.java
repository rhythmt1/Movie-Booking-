package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableGUI {

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


    //Table for movie tickets
    public JTableGUI() {
        frame = new JFrame();
        table = new JTable();
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
        pane.setBounds(0,0,880,200);
        setUp();
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



    public static void main(String[] args) {
        new JTableGUI();
    }

}
