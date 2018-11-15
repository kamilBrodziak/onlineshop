import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class AdminTest {
    private static Admin admin;
    private static DAO dao;

    @BeforeAll
    public static void setUp() {
        admin = new Admin(1, "ll", "[[", 1);
        dao = new DAO();

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

        assertNotNull(dao.getProduct("dupa"), "Product add to database check");
        System.out.println("Adding product to database test passed");
        admin.deleteProduct(dao.getProduct("dupa").getId());

    }

    @Test
    public void testIfProductDeletedFromDatabase() {
        ArrayList<String> testProductParams = new ArrayList<>();
        testProductParams.add("dupa");
        testProductParams.add("2048.99");
        testProductParams.add("20");
        testProductParams.add("1");
        admin.addProduct(testProductParams);

        admin.deleteProduct(dao.getProduct("dupa").getId());

        assertNull(dao.getProduct("dupa"), "Product delete from database check");
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