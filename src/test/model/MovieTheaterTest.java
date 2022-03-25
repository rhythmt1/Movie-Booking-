/*package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTheaterTest {
    private Ticket t1;
    private Ticket t2;
    private MovieTheater movieTheater;

    @BeforeEach
    void runBefore(){
        t1 = new Ticket(new Movie("The Batman", "7:30pm"));
        t2 = new Ticket(new Movie("The Batman", "7:30pm"));
        movieTheater = new MovieTheater("Encanto", "3:15pm");
    }

    @Test
    void testAddTicket() {
        assertFalse(movieTheater.isPurchased());

        movieTheater.addTicket(t1);
        assertTrue(movieTheater.isPurchased());
        assertEquals(1, movieTheater.getNumOfTickets());
        assertEquals(12.99, movieTheater.getTotalPrice());


        movieTheater.addTicket(t2);
        assertTrue(movieTheater.isPurchased());
        assertEquals(2, movieTheater.getNumOfTickets());
        assertEquals(25.98, movieTheater.getTotalPrice());

    }

    @Test
    void testGiveRating() {
        assertEquals(0, movieTheater.getRating());
        movieTheater.giveRating(7);
        assertEquals(7, movieTheater.getRating());

        movieTheater.giveRating(5);
        assertEquals(6, movieTheater.getRating());

        movieTheater.giveRating(8);
        assertEquals(6.7, movieTheater.getRating());



    }

}
*/