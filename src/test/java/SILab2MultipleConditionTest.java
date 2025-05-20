import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class SILab2MultipleConditionTest {

    private final String CARD = "1111222233334444";

    @Test
    void allConditionsFalse() {
        // price=300 (<=300), discount=0, quantity=10 (<=10)
        Item it = new Item("X", 10, 300, 0.0);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(300 * 10, sum);
    }

    @Test
    void onlyPriceTrue() {
        // price>300, others false
        Item it = new Item("X", 10, 301, 0.0);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(-30 + 301 * 10, sum);
    }

    @Test
    void onlyDiscountTrue() {
        // discount>0, others false
        Item it = new Item("X", 10, 300, 0.1);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(-30 + 300 * 0.9 * 10, sum, 1e-6);
    }

    @Test
    void onlyQuantityTrue() {
        // quantity>10, others false
        Item it = new Item("X", 11, 300, 0.0);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(-30 + 300 * 11, sum);
    }

    @Test
    void priceAndDiscountTrue() {
        // price>300 AND discount>0, quantity<=10
        Item it = new Item("X", 10, 301, 0.2);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(-30 + 301 * 0.8 * 10, sum, 1e-6);
    }

    @Test
    void priceAndQuantityTrue() {
        // price>300 AND quantity>10, discount=0
        Item it = new Item("X", 11, 301, 0.0);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(-30 + 301 * 11, sum);
    }

    @Test
    void discountAndQuantityTrue() {
        // discount>0 AND quantity>10, price<=300
        Item it = new Item("X", 11, 300, 0.2);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(-30 + 300 * 0.8 * 11, sum, 1e-6);
    }

    @Test
    void allThreeTrue() {
        // price>300, discount>0, quantity>10
        Item it = new Item("X", 11, 301, 0.2);
        double sum = SILab2.checkCart(List.of(it), CARD);
        assertEquals(-30 + 301 * 0.8 * 11, sum, 1e-6);
    }
}
