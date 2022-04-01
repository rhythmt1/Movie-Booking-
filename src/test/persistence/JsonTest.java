package persistence;


import model.Movie;
import model.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    //This class is modelled after JsonSerializationDemo project.

    protected void checkTicket(Movie movie, Ticket ticket) {
        assertEquals(movie, ticket.getMovie());
    }

}
