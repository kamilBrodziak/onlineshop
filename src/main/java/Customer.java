import java.util.Scanner;

public class Customer extends User {
    public Customer(int id, String login, String password, ProductController productController) {
        super(id, login, password, productController);
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
}
