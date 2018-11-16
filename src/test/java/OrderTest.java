import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
    }

    @Test
    public void testIfPaymentIsCorrectlyCounted() {
        Product product = new Product(656, "dd", BigDecimal.valueOf(65.3), 12, 1);
        Product product1 = new Product(636, "ddd", BigDecimal.valueOf(21.1), 11, 1);
        order.getBasket().addProduct(product, 3);
        order.getBasket().addProduct(product1, 4);

        assertEquals(BigDecimal.valueOf(280.30), order.pay(), "Payment value");
        System.out.println("Order payment test passed");
    }

    @Test
    public void testIfProductAddedToBasket() {
        String[] productParams = {"pineapple", "4"};
        order.addToBasket(productParams);

        assertEquals(4, order.getBasket().getAmount((Product)order.getBasket().getIterator().next()),
                "Test product add to basket");

        assertFalse(order.addToBasket(new String[]{"pineapple", "dd"}), "Product with wrong amount add");
        assertFalse(order.addToBasket(new String[]{"dzwig", "4"}), "Product not existing in database add");
        System.out.println("Product add from order to basket test passed");
    }

    @Test
    public void testIfProductDeletedFromBasket() {
        order.addToBasket(new String[]{"pineapple", "4"});
        order.deleteProduct("pineapple");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {order.getBasketIterator().next();});
        System.out.println("Product deleted from basket by order call test passed");
    }

    @Test
    public void testIfQuantityOfProductChangeInBasket() {
        order.addToBasket(new String[]{"pineapple", "4"});

        order.changeProductQuantinty(new String[]{"pineapple", "2"});

        assertEquals(2, order.getBasket().getAmount((Product)order.getBasketIterator().next()), "Quantity change of product in basket");

        assertFalse(order.changeProductQuantinty(new String[]{"pup", "3"}), "Quantity change of product which is not in basket");

        assertFalse(order.changeProductQuantinty(new String[]{"pineapple", "d"}), "Quantity change of product with invalid amount");
        System.out.println("Quantity change test passed");
    }
}