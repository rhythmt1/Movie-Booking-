package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTableGUI table;






    //EFFECTS: constructs main page
    @SuppressWarnings("methodlength")
    public MainPageGUI() {

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
            table = new JTableGUI();
            frame2.setVisible(false);

        } else if (o == button2) {
            table.saveTable();
        } else if (o == button3) {
            table.loadTable();
        }
    }




    public static void main(String[] args) {
        new MainPageGUI();
    }
}
