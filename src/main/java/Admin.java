import java.util.Scanner;

public class Admin extends User {
    public Admin(int id, String login, String password, ProductController productController) {
        super(id, login, password, productController);
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        String choice = "0";
        while(choice != "0") {
            System.out.println("1.Show products\t2.Add product\t3.Delete product\t0.Quit");
            if(scanner.hasNextLine()) {
                choice = scanner.nextLine();
            }
            switch (choice) {
                case "1":
                    DAO.printProducts();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "0":
                    System.out.println("Logout");
                    return;
                default:
                    System.out.println("No such option.");
            }
        }
    }

    private void addProduct() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please type in the name of the product:");
        String name = input.next();
        System.out.println("Please type in the price:");
        float price = input.nextFloat();
        System.out.println("Please type in the amount:");
        int amount = input.nextInt();
        System.out.println("Please type in category_id:");
        int category_id = input.nextInt();

        DAO.addProduct(name, price, amount, category_id);

    }

    private void deleteProduct() {
        Scanner input = new Scanner(System.in);
        int id = -1;
        if (input.hasNextInt()) {
            id = input.nextInt();
        }

        DAO.deleteProduct(id);
    }
}
