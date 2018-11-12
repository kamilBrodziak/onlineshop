import java.util.Scanner;

public class Admin extends User {
    public Admin(int id, String login, String password, ProductController productController) {
        super(id, login, password, productController);
    }

    public void menu() {
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
