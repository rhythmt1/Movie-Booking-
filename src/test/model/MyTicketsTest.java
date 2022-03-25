package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyTicketsTest {
    private MovieTheater myTickets1;
    private Ticket t1;
    private Ticket t2;
    @BeforeEach

    void runBefore(){
        myTickets1 = new MovieTheater();
         t1 = new Ticket(new Movie("The Batman", "7:30pm"));
         t2 = new Ticket(new Movie("The Batman", "7:30pm"));
    }

    @Test
    void testAddTicket() {
        assertFalse(myTickets1.isPurchased());

        myTickets1.addTicket(t1);
        assertTrue(myTickets1.isPurchased());
        assertEquals(1, myTickets1.getNumOfTickets());
        assertEquals(12.99, myTickets1.getTotalPrice());


        myTickets1.addTicket(t2);
        assertTrue(myTickets1.isPurchased());
        assertEquals(2, myTickets1.getNumOfTickets());
        assertEquals(25.98, myTickets1.getTotalPrice());

    }







}
