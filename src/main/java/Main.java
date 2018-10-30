import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws Exception {

        System.out.println("Main menu:");
        System.out.println("1. Show Products");

        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("1") ) {
            try {
                DAO.printProducts();
            } catch (Exception e) { 
                System.out.println("Exception e occured. Sad face.");

            }
        }

    }
}