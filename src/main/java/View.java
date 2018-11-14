import java.util.ArrayList;
import java.util.Scanner;

public class View {

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
            System.out.println("1.Show products\n2.Add product\n3.Delete product\n0.Quit");
            if(scanner.hasNextLine()) {
                choice = scanner.nextLine();
            }
            switch (choice) {
                case "1":
                    DAO.printProducts();
                    break;
                case "2":
                    admin.addProduct(View.getNewProductParameters());
                    break;
                case "3":
                    admin.deleteProduct(View.getIdProductToDelete());
                    break;
                case "0":
                    System.out.println("Logout");
                    break;
                default:
                    System.out.println("No such option.");
            }
        }
    }
}
