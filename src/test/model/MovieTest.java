package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    private Movie movie;
    @BeforeEach

    void runBefore() {
        movie = new Movie("Encanto", "3:15pm");
    }

    @Test
    void testGiveRating() {
        assertEquals(0, movie.getRating());
        movie.giveRating(7);
        assertEquals(7, movie.getRating());

        movie.giveRating(5);
        assertEquals(6, movie.getRating());

        movie.giveRating(8);
        assertEquals(6.7, movie.getRating());



    }
}
