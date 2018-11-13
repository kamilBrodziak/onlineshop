import java.util.Iterator;
import java.util.Scanner;

public class Customer extends User {
    public Customer(int id, String login, String password, int user_type_id) {
        super(id, login, password, user_type_id);
    }


    public void createNewOrder() {
        Order order = new Order();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\033\143");
        System.out.println("New order creator.");

        String quitOption = "0";
        String choose = "";

        while(!choose.equals(quitOption) && !choose.equals("1")) {
            System.out.println("\033\143");
            System.out.println("1.Pay\n2.Show basket\n3.Add product\n4.Delete product\n5.Change product quantity\n6.Print products\n0.Quit");
            if(scanner.hasNextLine()) {
                choose = scanner.nextLine();
            }

            switch(choose) {
                case "1":
                    order.pay();
                    break;
                case "2":
                    int isRunning = 1;
                    while (isRunning == 1) {
                        System.out.println("\033\143");
                        viewBasket(order);
                        System.out.println("(0) Back");
                        int goBack = scanner.nextInt();
                        if (goBack == 0){
                            isRunning = 0;
                        }
                    }
                    break;
                case "3":
                    try {
                        DAO.printProducts();
                    } catch (Exception e) {
                        System.out.println("Exception e occured. Sad face.");
                    }
                    addToBasket(order);
                    break;
                case "4":
                    System.out.println("\033\143");
                    viewBasket(order);
                    deleteProduct(order);
                    break;
                case "5":
                    System.out.println("\033\143");
                    viewBasket(order);
                    changeProductQuantinty(order);
                    break;
                case "6":
                    System.out.println("\033\143");
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
            while (iterator.hasNext()) {
                Product product = (Product) iterator.next();
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
        if(DAO.getProduct(name) != null) {
            order.addToBasket(DAO.getProduct(name), amount);
        }
    }

    @Override
    public void menu() {
        System.out.println("\033\143");
        Scanner scanner = new Scanner(System.in);
        String choice = "-1";
        while(!choice.equals("0")) {
            System.out.println("(1) Show products\n(2) Create new order\n(0) Quit");
            if(scanner.hasNextLine()) {
                choice = scanner.nextLine();
            }
            switch (choice) {
                case "1":
                    try {
                        DAO.printProducts();
                    } catch (Exception e) {
                        System.out.println("Exception e occured. Sad face.");
                    }
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
                if ((++i + "").equals(choice)) {
                    order.removeProduct((Product)iterator.next());
                    break;
                } else {
                    iterator.next();
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
