package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TicketTest {
    private Ticket ticket;

    @BeforeEach
    void runBefore() {
         ticket = new Ticket(new Movie("Encanto", "3:15pm"));

    }

    @Test
    void testSelectSeat() {
        assertEquals(0, ticket.getSeatNum());
        ticket.selectSeat(43);
        assertEquals(43, ticket.getSeatNum());

    }


}
