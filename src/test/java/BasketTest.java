import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Iterator;

class BasketTest {
    private static Basket basket;

    @BeforeAll
    public static void setUp() {
        basket = new Basket();

    }


    @Test
    public void testIfProductIsAddedToBasket() {
        Product product = new Product(999, "dupa", BigDecimal.valueOf(99.99), 12, 2);

        basket.addProduct(product, -2);
        assertEquals(0, basket.getAmount(product), "Product with negative amount add test");

        basket.addProduct(product, 5);
        assertEquals(5, basket.getAmount(product), "Product properly add test");

        basket.addProduct(product, 6);
        assertEquals(11, basket.getAmount(product), "Same product add again test");

        basket.deleteProduct(product);
        basket.addProduct(product, 13);
        assertEquals(0, basket.getAmount(product), "Too much amount given in add product test");

        System.out.println("Adding product to basket test");
        basket.deleteProduct(product);
    }


    @Test
    public void testGetAmountOfProductIsInBasket() {
        Product product = new Product(999, "dupa", BigDecimal.valueOf(99.99), 12, 2);

        assertEquals(0, basket.getAmount(product), "Amount of product which is not in basket");
        System.out.println("Getting amount tests passed");

        basket.addProduct(product, 9);
        assertEquals(9, basket.getAmount(product), "Amount of product in the basket");
    }

    @Test
    public void testEditAmountOfProductInBasket() {
        Product product = new Product(999, "dupa", BigDecimal.valueOf(99.99), 12, 2);
        basket.editProductQuantity(product, 4);
        assertEquals(0, basket.getAmount(product), "Edit product which is not in basket");

        basket.addProduct(product, 5);
        basket.editProductQuantity(product, 12);
        assertEquals(12, basket.getAmount(product), "Amount change of product");

        basket.editProductQuantity(product, 13);
        assertEquals(12, basket.getAmount(product), "Inproper amount change of product");

        basket.editProductQuantity(product, -1);
        assertEquals(0, basket.getAmount(product), "Negative amount change of product");
        basket.deleteProduct(product);
    }

    @Test
    public void testDeleteProductFromBasket() {
        Product product = new Product(999, "dupa", BigDecimal.valueOf(99.99), 12, 2);
        basket.addProduct(product, 11);
        basket.deleteProduct(product);
        assertEquals(0, basket.getAmount(product), "Product amount in basket after delete");
        assertEquals(12, product.getAmount(), "Amount of product after deleting from basket");
    }

    @Test
    public void testBasketIterator() {
        Iterator iterator = basket.getIterator();

        while(iterator.hasNext()) {
            assertEquals(Product.class, iterator.next().getClass(), "Object type returned by basket iterator");
        }


        System.out.println("Basket iterator test passed");
    }
}