package utility;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.format.DateTimeParseException;

public class ParseUtilityTests {

    @Test
    void throwsOnBadDateFormat() {
        assertThrows(DateTimeParseException.class, () -> ParseUtility.parseDate("10-10-2006"));
    }

    @Test
    void returnsCorrectID() {
        assertEquals(25140001, ParseUtility.parseUID("25-140001"));
        assertEquals(25140002, ParseUtility.parseUID("25-140002"));
        assertEquals(25141111, ParseUtility.parseUID("25-141111"));
    }

    @Test
    void returnsCorrectStringID() {
        assertEquals("25-140001", ParseUtility.unparseUID(25140001));
        assertEquals("25-140002", ParseUtility.unparseUID(25140002));
        assertEquals("25-140003", ParseUtility.unparseUID(25140003));
    }

    @Test
    void failsAtBadIDFormat() {
        assertThrows(IllegalArgumentException.class, () -> ParseUtility.parseUID("25-1400011"));
        assertThrows(IllegalArgumentException.class, () -> ParseUtility.parseUID("25-14001"));
        assertThrows(IllegalArgumentException.class, () -> ParseUtility.unparseUID(251400011));
        assertThrows(IllegalArgumentException.class, () -> ParseUtility.unparseUID(2514001));
        assertThrows(IllegalArgumentException.class, () -> ParseUtility.parseUID("25-14ab0a"));
        assertThrows(IllegalArgumentException.class, () -> ParseUtility.parseUID("-_.*!ab0a"));
    }
}
