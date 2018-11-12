import java.util.Iterator;
import java.util.Scanner;

public abstract class User {
    private int id;
    private String login;
    private String password;
    private int user_type_id;

    public User(int id, String login, String password, int user_type_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.user_type_id = user_type_id;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return user_type_id;
    }

    public abstract void menu();
}
