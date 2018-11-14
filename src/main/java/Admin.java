import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    public Admin(int id, String login, String password, int user_type_id) {
        super(id, login, password, user_type_id);
    }

    @Override
    public void menu() {
        View.adminMenu(this);
    }

    public void addProduct(ArrayList<String> productParams) {
        DAO.addProduct(productParams.get(0), Float.parseFloat(productParams.get(1)),
                Integer.parseInt(productParams.get(2)), Integer.parseInt(productParams.get(3)));

    }

    public void deleteProduct(int id) {
        DAO.deleteProduct(id);
    }
}
