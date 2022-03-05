package persistence;

import model.Movie;
import model.Ticket;
import model.MyTickets;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
    //This class is modelled after JsonSerializationDemo project.

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MyTickets mt = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            MyTickets mt = reader.read();
            assertEquals(0, mt.getNumOfTickets());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            MyTickets mt = reader.read();
            List<Ticket> tickets = mt.getTickets();
            assertEquals(2, tickets.size());
            checkTicket("Encanto", tickets.get(0);
            checkTicket("Uncharted", tickets.get(1);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}