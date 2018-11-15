import java.util.Iterator;
import java.util.Scanner;

public class Customer extends User {
    public Customer(int id, String login, String password, int user_type_id) {
        super(id, login, password, user_type_id);
    }


    public void createNewOrder() {
        Order order = new Order();
        View.orderMenu(this, order);
    }

    @Override
    public void menu() {
        View.customerMenu(this);
    }

}
