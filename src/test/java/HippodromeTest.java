import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    void checkFirstArgNullException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null),
                "Expected constructor() to throw if list null"
        );
    }
    @Test
    void checkFirstArgNullExceptionMessage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null),
                "Expected constructor() to throw if list null"
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    void checkFirstArgEmptyListException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()),
                "Expected constructor() to throw if list empty"
        );
    }
    @Test
    void checkFirstArgEmptyListExceptionMessage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()),
                "Expected constructor() to throw if list empty"
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    void getHorsesReturnsSameList() {
        List<Horse> listHorses = new ArrayList<Horse>();
        for (int i = 0; i < 30; i++) {
            listHorses.add(new Horse(String.valueOf(i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        List<Horse> result = hippodrome.getHorses();
        assertEquals(listHorses, result);
    }
    @Test
    void checkMoveEveryHorsesHorses() {
        List<Horse> listHorses = new ArrayList<Horse>();
        for (int i = 0; i < 50; i++) {
            listHorses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        hippodrome.move();
        for (Horse horse : listHorses) {
            verify(horse).move();
        }
    }
    @Disabled("Тест временно отключен")
    @Test
    void getWinner() {
        List<Horse> listHorses = new ArrayList();
        for (int i = 1; i < 5; i++) {
            listHorses.add(new Horse(String.valueOf(i), i, i));
        }
        Horse lastHorse = listHorses.get(listHorses.size() - 1);
        Hippodrome hippodrome = new Hippodrome(listHorses);
        Horse actualWinner = hippodrome.getWinner();
        assertEquals(lastHorse, actualWinner);
    }

}
