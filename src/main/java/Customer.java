import java.util.Iterator;
import java.util.Scanner;

public class Customer extends User {
    public Customer(int id, String login, String password, int user_type_id) {
        super(id, login, password, user_type_id);
    }


    public void createNewOrder() {
        Order order = new Order();

        Scanner scanner = new Scanner(System.in);

        System.out.println("New order creator.");

        String quitOption = "0";
        String choose = "";

        while(!choose.equals(quitOption) && !choose.equals("1")) {
            System.out.println("1.Pay\n2.Show basket\n3.Add product\n4.Delete product\n5.Change product quantity\n6.Print products\n0.Quit");
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
                    addToBasket(order);
                    break;
                case "4":
                    deleteProduct(order);
                    break;
                case "5":
                    changeProductQuantinty(order);
                    break;
                case "6":
                    DAO.printProducts();
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
            Product product = (Product)iterator.next();
            System.out.println(++i + "\t\t" + product.getName() + "\t\t" + order.getBasket().getAmount(product));
        }
    }

    private void addToBasket(Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide product name");
        String name = "";
        if(scanner.hasNextLine()) {
            name = scanner.nextLine();
        }
        int amount = 0;
        System.out.println("Provide amount");
        if(scanner.hasNextInt()) {
            amount = scanner.nextInt();
        }
        order.addToBasket(DAO.getProduct(name), amount);
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        String choice = "-1";
        while(choice != "0") {
            System.out.println("1.Show products\n2.Create new order\n0.Quit");
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

    private void changeProductQuantinty(Order order) {
        System.out.println("Which product by name do you want do delete?");
        Scanner scanner = new Scanner(System.in);
        String name = "";
        if(scanner.hasNextLine()){
            name = scanner.nextLine();
        }
        System.out.println("Provide new amount");
        int amount = 0;
        if(scanner.hasNextInt()) {
            amount = scanner.nextInt();
        }

        order.editProductQuantity(name, amount);

    }
}
