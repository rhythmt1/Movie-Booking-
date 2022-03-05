package persistence;

import model.Movie;
import model.Ticket;
import model.MyTickets;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //This class is modelled after JsonSerializationDemo project.

    @Test
    void testWriterInvalidFile() {
        try {
            MyTickets mt = new MyTickets();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MyTickets mt = new MyTickets();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            mt = reader.read();
            assertEquals(0, mt.getNumOfTickets());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MyTickets mt = new MyTickets();
            mt.addTicket(new Ticket(new Movie("Encanto", "6:30pmm")));
            mt.addTicket(new Ticket(new Movie("Uncharted", "10:45pm")));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(mt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            mt = reader.read();
            List<Ticket> tickets = mt.getTickets();
            assertEquals(2, tickets.size());
            checkTicket("Encanto", tickets.get(0));
            checkTicket("Uncharted", tickets.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}