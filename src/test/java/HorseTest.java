import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    void checkFirstArgNullException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1.5, 1.5),
                "Expected constructor() to throw if name null"
        );
    }
    @Test
    void checkFirstArgNullExceptionMessage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1.5, 1.5),
                "Expected constructor() to throw if name null"
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }


    @ParameterizedTest
    @CsvSource({
            "'', 1.5, 1.5",
            "' ', 1.5, 1.5",
            "'  ', 1.5, 1.5",
    })
    void checkFirstArgEmptyException(String name, double speed, double distance) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, speed, distance),
                "Expected constructor() to throw if name space"
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'', 1.5, 1.5",
            "' ', 1.5, 1.5",
            "'  ', 1.5, 1.5",
    })
    void checkFirstArgEmptyExceptionMessage(String name, double speed, double distance) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, speed, distance),
                "Name cannot be blank."
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void checkSecondArgNullException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Aleksander", -1.5, 1.5),
                "Expected constructor() to throw if speed negative"
        );
    }
    @Test
    void checkSecondArgNullExceptionMessage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Aleksander", -1.5, 1.5),
                "Expected constructor() to throw if speed negative"
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void checkThirdArgNullException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Aleksander", 1.5, -1.5),
                "Expected constructor() to throw if distance negative"
        );
    }
    @Test
    void checkThirdArgNullExceptionMessage() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Aleksander", 1.5, -1.5),
                "Expected constructor() to throw if distance negative"
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    void getName() {
        String name = "Aleksander";
        Horse horse = new Horse("Aleksander", 1.5, 1.5);
        assertEquals(name, horse.getName());
    }
    @Test
    void getSpeed() {
        double speed = 1.5;
        Horse horse = new Horse("Aleksander", speed, 1.5);
        assertEquals(speed, horse.getSpeed());
    }
    @Test
    void getDistance() {
        double distance = 1.5;
        Horse horse = new Horse("Aleksander", 1.5, distance);
        assertEquals(distance, horse.getDistance());
    }
    @Test
    void getNullDistance() {
        Horse horse = new Horse("Aleksander", 1.5);
        assertEquals(0.0, horse.getDistance());
    }
    @Test
    void moveCallsGetRandomDouble() {
        Horse horse = new Horse("Horse", 10);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @CsvSource({
            "10.0, 20.0, 0.2, 22.0",
            "10.0, 20.0, 0.5, 25.0",
            "10.0, 20.0, 0.9, 29.0"
    })
    void moveResult(double speed, double initialDistance, double randomValue, double expectedDistance) {
        Horse horse = new Horse("Aleksander", speed, initialDistance);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic
                    .when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(randomValue);
            horse.move();
            assertEquals(expectedDistance, horse.getDistance(), 0.000001);
        }
    }

}
