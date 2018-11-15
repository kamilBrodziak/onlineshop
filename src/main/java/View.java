import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class View {
    private static DAO dao = new DAO();

    public static ArrayList<String> getNewProductParameters() {
        ArrayList<String> productParams = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Please type in the name of the product:");
        productParams.add(input.nextLine());
        System.out.println("Please type in the price:");
        String temp = input.nextLine();
        if (temp.matches("[0-9.]*")) {
            productParams.add(temp);
        } else {
            throw new IllegalArgumentException("Price differ from float value.");
        }
        System.out.println("Please type in the amount:");
        temp = input.nextLine();
        if (temp.matches("[0-9]*")) {
            productParams.add(temp);
        } else {
            throw new IllegalArgumentException("Price differ from int value.");
        }
        System.out.println("Please type in category_id:");
        temp = input.nextLine();

        if (temp.matches("[0-9]*")) {
            productParams.add(temp);
        } else {
            throw new IllegalArgumentException("Price differ from int value.");
        }

        return productParams;
    }

    public static int getIdProductToDelete() {
        Scanner input = new Scanner(System.in);
        int id = -1;
        if (input.hasNextInt()) {
            id = input.nextInt();
        }
        return id;
    }

    public static void adminMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        String choice = "-1";
        while(!choice.equals("0")) {
            System.out.println("1.Show products\n2.Add product\n3.Delete product\n6.Edit Product\n0.Quit");
            if(scanner.hasNextLine()) {
                choice = scanner.nextLine();
            }
            switch (choice) {
                case "1":
                    printTable(dao.getProductList());
                    break;
                case "2":
                    admin.addProduct(getNewProductParameters());
                    break;
                case "3":
                    admin.deleteProduct(getIdProductToDelete());
                    break;
                case "6":
                    dao.editProduct();
                    break;
                case "0":
                    System.out.println("Logout");
                    break;
                default:
                    System.out.println("No such option.");
            }
        }
    }

    public static void showBasket(Order order) {
        System.out.println("Your basket:");
        Iterator iterator = order.getBasketIterator();
        int i = 0;
        String leftAlignFormat = "|  %-4d | %-20s |  %-6d  |%n";

        System.out.format("+-------+----------------------+----------+%n");
        System.out.format("|  ID   |  Name                |  Amount  |%n");
        System.out.format("+-------+----------------------+----------+%n");
        while( iterator.hasNext()) {
            Product product = (Product)iterator.next();
            System.out.format(leftAlignFormat, ++i, product.getName(), order.getBasket().getAmount(product));
        }
        System.out.format("+-------+----------------------+----------+%n");
    }

    public static String[] getProductInput() {
        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);
        input[0] = getProductName();

        System.out.println("Provide amount");
        if(scanner.hasNextLine()) {
            input[1] = scanner.nextLine();
        }

        return input;
    }

    public static String getProductName() {
        Scanner scanner = new Scanner(System.in);

        String name = "";
        System.out.println("Provide product name");
        if(scanner.hasNextLine()) {
            name = scanner.nextLine();
        }
        return name;
    }

    public static void customerMenu(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        String choice = "-1";
        System.out.println("\033\143" + "LOGIN AS " + customer.getLogin());
        while(!choice.equals("0")) {
            System.out.println("(1) Show products\n(2) Create new order\n(0) Quit");
            if(scanner.hasNextLine()) {
                choice = scanner.nextLine();
            }
            switch (choice) {
                case "1":
                    System.out.println("\033\143");
                    printTable(dao.getProductList());
                    break;
                case "2":
                    System.out.println("\033\143");
                    customer.createNewOrder();
                    break;
                case "0":
                    System.out.println("\033\143");
                    System.out.println("Logout");
                    return;
                default:
                    System.out.println("\033\143No such option");
            }
        }
    }

    public static void orderMenu(Customer customer, Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033\143");
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
                    printCost(order.pay());
                    break;
                case "2":
                    int isRunning = 1;
                    while (isRunning == 1) {
                        System.out.println("\033\143");
                        View.showBasket(order);
                        System.out.println("(0) Back");
                        int goBack = scanner.nextInt();
                        if (goBack == 0){
                            isRunning = 0;
                        }
                    }
                    break;
                case "3":
                    printTable(dao.getProductList());
                    order.addToBasket(View.getProductInput());
                    break;
                case "4":
                    System.out.println("\033\143");
                    View.showBasket(order);
                    order.deleteProduct(View.getProductName());
                    break;
                case "5":
                    System.out.println("\033\143");
                    View.showBasket(order);
                    order.changeProductQuantinty(View.getProductInput());
                    break;
                case "6":
                    System.out.println("\033\143");
                    printTable(dao.getProductList());
                    break;
                case "0":
                    return;
                default:
                    System.out.println("No such option.");
            }

        }
    }

    public static String[] getUserLog() {
        String[] logParam = new String[2];

        Scanner scanner = new Scanner(System.in);
        System.out.println("\033\143Please enter login: \n");
        if(scanner.hasNextLine()) {
            logParam[0] = scanner.nextLine();
        }
        System.out.println("Please enter password: \n");
        if(scanner.hasNextLine()) {
            logParam[1] = scanner.nextLine();
        }
        return logParam;
    }

    public static void printMainMenu() {
        System.out.println("(1) Show all products");
        System.out.println("(2) Log in");
        System.out.println("(3) Register");
        System.out.println("(0) Exit");
    }

    public static void printCost(BigDecimal cost) {
        System.out.println("Your order cost: " + cost);
        System.out.println("13 1140 2004 0000 3702 7657 6565, send it and then we will send you your order.");
    }

    public static void printTable(List<String> stringList) {
        System.out.println("id\tname\tprice\tamount\tcategory_id");
        for(String row: stringList) {
            System.out.println(row);
        }
    }

    public static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in your login:");
        String login = scanner.nextLine();
        System.out.println("Please type in your password:");
        String password = scanner.nextLine();

        dao.register(login, password);
    }
}
