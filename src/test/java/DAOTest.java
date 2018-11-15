import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {
    private static DAO dao;

    @BeforeAll
    public static void setUpAll() {
        dao = new DAO();
    }

    @Test
    public void testIfConnectedToDatabase() {
        assertDoesNotThrow(() -> {dao.connect();}, "Database connection");
        try {
            dao.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Connection to database test passed");
    }

    @Test
    public void testIfDisconnectedFromDatabase() {
        try {
            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertDoesNotThrow(() -> {dao.disconnect();}, "Database disconnect");
        assertThrows(Exception.class, () -> {dao.disconnect();}, "Database second disconnect");
        System.out.println("Disconnected from database test passed");
    }

    @Test
    public void testIfProductsAreLoadedFromDatabase() {
        assertTrue(dao.getProductList().size() > 0, "Products loaded from database");
        System.out.println("Products loaded from database test passed");
    }

    @Test
    public void testIfProductDeletedFromDatabase() {
        dao.deleteProduct(1);
        assertNull(dao.getProduct("pencil"), "Product delete from database");
        System.out.println("Product delete from database test passed");
    }

    @Test
    public void testIfProductAddedToDatabase() {
        dao.addProduct("dupppka", 22f, 2, 2);

        assertNotNull(dao.getProduct("dupppka"), "Product add to database");
        System.out.println("Product add to database test passed");
    }

    @Test
    public void testIfProductGotFromDatabase() {
        dao.addProduct("test", 22f, 3, 1);

        assertNotNull(dao.getProduct("test"), "Product get which is in database");

        assertNull(dao.getProduct("dzik"), "Product get which is not in database");
        System.out.println("Product get from database test passed");
    }

    @Test
    public void testIfUserGotFromDatabase() {
        assertEquals(Admin.class, dao.getUser("admin", "admin").getClass(), "Admin get from database");
        assertEquals(Customer.class, dao.getUser("user1", "password").getClass(), "Customer get from database");
        assertNull(dao.getUser("kasjk", "jklsa"), "User get which is not in database");
        System.out.println("User get from database test passed");
    }

    @Test
    public void testIfRegisteredUserExistInDatabase() {
        dao.register("dupczyk", "tesz");
        assertNotNull(dao.getUser("dupczyk", "tesz"), "Customer register");
        System.out.println("User register to database test passed");
    }

    @AfterAll
    public static void onOut() {
        dao.restoreDatabase();
    }
}