import java.util.Iterator;
import java.util.Scanner;

public abstract class User {
    private int id;
    private String login;
    private String password;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public abstract void menu();
}
