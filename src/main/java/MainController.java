import java.util.Scanner;

public class MainController {
    private DAO dao = new DAO();

    public void run() {
        dao.restoreDatabase();
        Scanner input = new Scanner(System.in);
        System.out.println("\033\143");
        int option = 0;
        while (true) {
            View.printMainMenu();
            if(input.hasNextInt()) {
                option = input.nextInt();
            }
            switch (option){
                case 1:
                    View.printTable(dao.getProductList());
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    View.registerUser();
                    break;
                case 0:
                    System.exit(0);
                    break;
                case 666:
                    dao.restoreDatabase();
                    break;
            }
        }
    }
    private void userLogin() {
        String[] logParam = View.getUserLog();

        User user = dao.getUser(logParam[0], logParam[1]);
        if (user == null) {
            System.out.println("INVALID USERNAME OR PASSWORD");
            return;
        }
        if(user.getType() == 1) {
            ((Admin)user).menu();
        } else {
            ((Customer)user).menu();
        }
    }


}
