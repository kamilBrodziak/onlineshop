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

    private void viewBasket(Order order) {
        System.out.println("Your basket:");
        Iterator iterator = order.getBasketIterator();
        int i = 0;
        System.out.println("id\t\tname\t\tamount");
        while( iterator.hasNext()) {
            Product product = (Product)iterator.next();
            System.out.println(++i + "\t\t" + product.getName() + "\t\t" + product.getAmount());
        }
    }

    private void deleteProduct(Order order) {
        System.out.println("Which product do you want to delete?");
        Scanner scanner = new Scanner(System.in);
        String choice = "as";
        if(scanner.hasNextLine()) {
            choice = scanner.nextLine();
        }

        if(choice.matches("[0-9]")) {
            Iterator iterator = order.getBasketIterator();
            int i = 0;
            while(iterator.hasNext()) {
                if ((i + 1 + "").equals(choice)) {
                    order.removeProduct((Product)iterator.next());
                    break;
                }
            }
        }
    }
}
