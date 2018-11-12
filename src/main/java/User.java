import java.util.Iterator;
import java.util.Scanner;

public class User {
    private int id;
    private String login;
    private String password;
    protected ProductController productController;

    public User(int id, String login, String password, ProductController productController) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.productController = productController;
    }
}
