import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

class AdminTest {
    private static Admin admin;

    @BeforeAll
    public static void setUp() {
        admin = new Admin(1, "ll", "[[", 1);

    }

    @AfterEach
    public void onOut() {
    }

    @Test
    public void testIfProductAddedToDatabase() {
        ArrayList<String> testProductParams = new ArrayList<>();
        testProductParams.add("dupa");
        testProductParams.add("2048.99");
        testProductParams.add("20");
        testProductParams.add("1");

        admin.addProduct(testProductParams);

        assertNotNull(DAO.getProduct("dupa"), "Product add to database check");
        System.out.println("Adding product to database test passed");
        admin.deleteProduct(DAO.getProduct("dupa").getId());

    }

    @Test
    public void testIfProductDeletedFromDatabase() {
        ArrayList<String> testProductParams = new ArrayList<>();
        testProductParams.add("dupa");
        testProductParams.add("2048.99");
        testProductParams.add("20");
        testProductParams.add("1");
        admin.addProduct(testProductParams);

        admin.deleteProduct(DAO.getProduct("dupa").getId());

        assertNull(DAO.getProduct("dupa"), "Product delete from database check");
        System.out.println("Deleting product from database test passed");
    }


//    @Test
//    public void testIfMenuWorksProperly() {
//        assertDoesNotThrow(() -> {
//            admin.menu();
//        }, "Menu exception check");
//        System.out.println("Menu exception check passed");
//    }
}