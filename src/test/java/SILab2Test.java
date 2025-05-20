import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class SILab2Test {

    @Test
    void nullListThrows() {
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(null, "0123456789012345")
        );
        assertEquals("allItems list can't be null!", ex.getMessage());
    }

    @Test
    void nullItemNameThrows() {
        Item bad = new Item(null, 1, 100, 0);
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(List.of(bad), "0123456789012345")
        );
        assertEquals("Invalid item!", ex.getMessage());
    }

    @Test
    void emptyItemNameThrows() {
        Item bad = new Item("", 1, 100, 0);
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(List.of(bad), "0123456789012345")
        );
        assertEquals("Invalid item!", ex.getMessage());
    }

    @Test
    void mixedItemsValidSum() {
        Item a = new Item("A", 1,  50,   0   );
        Item b = new Item("B", 1, 350,   0   );
        Item c = new Item("C", 2, 200, 0.25 );
        double sum = SILab2.checkCart(List.of(a, b, c), "9999888877776666");
        // A: +50
        // B: -30 + 350 = +320 → total 370
        // C: -30 + 200*0.75*2 = +300 → total 370+300 = 670? Wait—recalculate:
        //   after A: sum=50
        //   B: sum=50-30+350 = 370
        //   C: sum=370-30 + (200*0.75*2=300) = 370-30+300 = 640
        assertEquals(640, sum);
    }

    @Test
    void nullCardNumberThrows() {
        Item x = new Item("X", 1, 10, 0);
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(List.of(x), null)
        );
        assertEquals("Invalid card number!", ex.getMessage());
    }

    @Test
    void invalidCardCharacterThrows() {
        Item y = new Item("Y", 1, 10, 0);
        String badCard = "1234ABCD5678EFGH";
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(List.of(y), badCard)
        );
        assertEquals("Invalid character in card number!", ex.getMessage());
    }
}
