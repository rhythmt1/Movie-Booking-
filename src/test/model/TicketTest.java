package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TicketTest {
    private Ticket ticket;
    private Movie encanto;

    @BeforeEach
    void runBefore() {
        encanto = new Movie("Encanto", "3:15pm");
         ticket = new Ticket(encanto);

    }

    @Test
    void testSelectSeat() {
        assertEquals(0, ticket.getSeatNum());
        ticket.selectSeat(43);
        assertEquals(43, ticket.getSeatNum());

    }

    @Test
    void testGetMovie() {
        assertEquals(encanto, ticket.getMovie());
    }


}
