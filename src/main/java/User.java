import java.util.Iterator;
import java.util.Scanner;

public class User {
    private int id;
    private String login;
    private String password;
    ProductController productController;

    public User(int id, String login, String password, ProductController productController) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.productController = productController;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        String choice = "0";
        while(choice != "0") {
            System.out.println("1.Show products\t2.Create new order\t0.Quit");
            if(scanner.hasNextLine()) {
                choice = scanner.nextLine();
            }
            switch (choice) {
                case "1":
                    break;
                case "2":
                    createNewOrder();
                    break;
                case "0":
                    System.out.println("Logout");
                    return;
                default:
                    System.out.println("No such option.");
            }
        }
    }

    public void createNewOrder() {
        Order order = new Order();

        Scanner scanner = new Scanner(System.in);

        System.out.println("New order creator.");

        String quitOption = "0";
        String choose = "";

        while(!choose.equals(quitOption) && !choose.equals("1")) {
            System.out.println("1.Pay\n2.Show basket\n3.Add product\n4.Delete product\n0.Quit");
            if(scanner.hasNextLine()) {
                choose = scanner.nextLine();
            }

            switch(choose) {
                case "1":
                    order.pay();
                    break;
                case "2":
                    viewBasket(order);
                    break;
                case "3":
                    order.addToBasket(productController.getProduct());
                case "4":
                    deleteProduct(order);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("No such option.");
            }

        }

    }

    private void viewBasket(Order order) {
        System.out.println("Your basket:");
        Iterator iterator = order.getBasketIterator();
        int i = 0;
        System.out.println("id\t\tname\t\tamount");
        while( iterator.hasNext()) {
            Product product = iterator.next();
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
                    order.removeProduct(iterator.next());
                    break;
                }
            }
        }
    }
}
