package persistence;

import model.MovieTheater;
import model.Ticket;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {
    //This class is modelled after JsonSerializationDemo project.

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieTheater mt = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTickets() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyTickets.json");
        try {
            MovieTheater mt = reader.read();
            assertEquals(0, mt.getNumOfTickets());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTickets() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralTickets.json");
        try {
            MovieTheater mt = reader.read();
            List<Ticket> tickets = mt.getTickets();
            assertEquals(2, tickets.size());
            checkTicket(mt.findMovie("Encanto", "6:30pm"), tickets.get(0));
            checkTicket(mt.findMovie("Uncharted", "10:45pm"), tickets.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}