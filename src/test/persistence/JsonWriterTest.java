package persistence;

import model.MovieTheater;
import model.Ticket;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //This class is modelled after JsonSerializationDemo project.

    @Test
    void testWriterInvalidFile() {
        try {
            MovieTheater mt = new MovieTheater();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTickets() {
        try {
            MovieTheater mt = new MovieTheater();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTickets.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTickets.json");
            mt = reader.read();
            assertEquals(0, mt.getNumOfTickets());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTickets() {
        try {
            MovieTheater mt = new MovieTheater();
            mt.addTicket(new Ticket(mt.getMovies().get(1)));
            mt.addTicket(new Ticket(mt.getMovies().get(3)));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTickets.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTickets.json");
            mt = reader.read();
            List<Ticket> tickets = mt.getTickets();
            assertEquals(2, tickets.size());
            checkTicket(mt.getMovies().get(1), tickets.get(0));
            checkTicket(mt.getMovies().get(3), tickets.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}