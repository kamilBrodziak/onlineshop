import java.util.*;


public class Main {
    HashMap<String, String> logs = new HashMap<>();


    public static void main(String[] args) throws Exception {

        while (true) {
            printMenu();
            
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option){
                case 1:
                try {    
                    DAO.printProducts();
                } catch (Exception e) {
                    System.out.println("Exception e occured. Sad face.");
                }
                    break;

                case 2:

                    break;

                case 3:
                    break;

                case 4:
                    System.exit(0);
                    break;
                
                case 5:
                    System.out.println("Which id to delete?");    
                    int id = input.nextInt();   
                    DAO.deleteProduct(id);
                    break;
                
                case 666: 
                    DAO.restoreDatabase();
                    break;
            }
        }
    }


    public static void printMenu() {
        System.out.println("(1) Show all products");
        System.out.println("(2) Log in");
        System.out.println("(3) Register");
        System.out.println("(4) Exit");
        System.out.println("(5) Delete Product");
    }


    public void printUserMenu() {
        System.out.println("(1) Show all products");
        System.out.println("(2) Show categories");
        System.out.println("(3) Search product by category");
        System.out.println("(4) Add to basket");
        System.out.println("(5) Exit");
    }

    public void printAdminMenu() {
        System.out.println("(1) Show all products");
        System.out.println("(2) Add new product");
        System.out.println("(3) Remove product");
        System.out.println("(4) Set feature category");
        System.out.println("(5) Ban user! ;) ");
    }

    public static void printProducts() {
        ArrayList<Product> productsList = new ArrayList<Product>();
        for (int i = 0; i < productsList.size(); i++) {
            System.out.println(productsList.get(i));
        }
    }
}

    //    public static void loginSystem(HashMap<String, Integer> logs){
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter login: \n");
//        String login = input.nextLine();
//        if (logs.containsValue(login)){
//            System.out.println("Enter password: \n");
//            String password = input.nextLine();
//            if ()
//        }
//    }