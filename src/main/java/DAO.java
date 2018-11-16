import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.lang.*;
import java.math.BigDecimal;

public class DAO {
    private Connection c;
    private Statement stmt;

    public DAO() {
        c = null;
        stmt = null;
    }

    public void connect() throws Exception{
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/test.db");
        stmt = c.createStatement();
    }

    public void disconnect() throws Exception {
        stmt.close();
        c.close();
    }

    
    public List<String> getProductList() {
        try {
            connect();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PRODUCTS;" );
            List<String> products = new ArrayList<>();
            while ( rs.next() ) {
                String id = rs.getInt("id") + "";
                String  name = rs.getString("name");
                String price  = rs.getFloat("Price") + "";
                String  amount = rs.getString("amount");
                String category_id = rs.getInt("category_id") + "";

                products.add(id + "\t" + name + "\t" + price + "\t" + amount + "\t" + category_id);
            }
            disconnect();
            return products;
        } catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
        }
        return null;
    }



    public void deleteProduct(int id) {
        try {
            connect();
            c.setAutoCommit(false);
            String sql = "DELETE from PRODUCTS where ID=" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            System.out.println("Product deleted successfully");
            disconnect();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }


    public void addProduct(String name, Float price, int amount, int category_id) {
        try {
            connect();
            c.setAutoCommit(false);
            String sql = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES ('" + name + "'," + price + "," + amount + ", 'True'," + category_id + ");" ;
            stmt.executeUpdate(sql);
            c.commit();
            System.out.println("Product added successfully");
            disconnect();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }


//    public void addCategory(String name, String isAvailable, int category_type_id) {
//        try {
//            connect();
//            c.setAutoCommit(false);
//            String sql = "INSERT INTO CATEGORIES (NAME,isAvailable,category_type_id) " + "VALUES ('" + name + "'," + isAvailable + "," + category_type_id + ");" ;
//            stmt.executeUpdate(sql);
//            c.commit();
//            disconnect();
//            System.out.println("Category added successfully");
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//    }

    
    public  Product getProduct(String product_name) {
        Product temp = null;

        try {
            connect();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PRODUCTS;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int amount  = rs.getInt("amount");
                int category_id = rs.getInt("category_id");
                if (name.equalsIgnoreCase(product_name)) {
                    temp = new Product(id, name, BigDecimal.valueOf(price), amount, category_id);
                }
            }
            disconnect();
        } catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
        }
        return temp;
    }


    public User getUser(String login, String password) {

        try {
            connect();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass  = rs.getString("password");
                int user_type_id = rs.getInt("user_type_id");
                if (name.equals(login) && pass.equals(password)) {
                    if (user_type_id == 1) {
                        Admin admin = new Admin(id, login, pass, user_type_id);
                        disconnect();
                        return admin;
                    } else {
                        Customer customer = new Customer(id, login, pass, user_type_id);
                        disconnect();
                        return customer;
                    }
                }
            }
            disconnect();
        } catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
        }
        return null;
    }


    public void register(String login, String password) { //id 1 for admin , 2 for user


        try {
            connect();
            c.setAutoCommit(false);
            String sql = "INSERT INTO USERS (name,password,user_type_id) " + "VALUES ('" + login + "','" + password + "', 2 );" ;
            stmt.executeUpdate(sql);
            c.commit();
            disconnect();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("User added successfully");

    }
    

    public void editProduct(){
//        System.out.println("Which product id to edit?");
//        Scanner scanner = new Scanner(System.in);
//        int inputID = scanner.nextInt();
//        System.out.println("What do you want to edit? [Name / Price / Amount] only Name works now ");
//
//        String inputColumn = scanner.nextLine();
//        if (scanner.hasNextLine() ) {
//            inputColumn = scanner.nextLine();
//            inputColumn = inputColumn.toUpperCase();
//        }
//
//        try {
//            connect();
//            c.setAutoCommit(false);
//
//            if (inputColumn.equals("NAME")) {
//                System.out.println("Please type in new name:");
//                String newName = scanner.nextLine();
//                stmt = c.createStatement();
//                String sql = "UPDATE PRODUCTS SET NAME = '" + newName + "' WHERE ID = " + inputID + ";";
//                stmt.executeUpdate(sql);
//                c.commit();
//                System.out.println("sql executed");

//            }
            // if (inputColumn.equals("PRICE")) {
            //     System.out.println("Please type in new price:");
            //     int newPrice = scanner.nextInt();
            // }
            // if (inputColumn.equals("AMOUNT")) {
            //     System.out.println("Please type in new amount:");
            //     int newAmount = scanner.nextInt();
            // }
//            disconnect();
//        } catch ( Exception e ) {
//         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//         System.exit(0);
//        }
    }

    

    public void restoreDatabase() {
        File file = new File("src/main/resources/test.db");
        if(file.delete()){
            System.out.println("test.db File deleted");
        } else {
            System.out.println("File test.db doesn't exists");
        }
      
        try {
            connect();
            System.out.println("Opened database successfully");
            String sql = "CREATE TABLE `CATEGORIES`" +
                "( `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`name`	TEXT NOT NULL, " +
                "`isAvailable`	BOOLEAN NOT NULL, " +
                "`category_type_id`	INT NOT NULL, " + 
                "FOREIGN KEY(`category_type_id`) REFERENCES `CATEGORIES`)" ;
             String sql2 = "CREATE TABLE `CATEGORY_TYPES` ( " +
                " `id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`name`	TEXT NOT NULL)";
             String sql3 = " CREATE TABLE `ORDERS` ( " +
                "`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + 
                "`user_id`	INT NOT NULL, " +
                "`order_created_at`	INT NOT NULL, " +
                "`order_status_id`	INT NOT NULL, " +
                "FOREIGN KEY(`user_id`) REFERENCES `USERS`(`id`) ) " ;
             String sql4 = "CREATE TABLE `ORDER_STATUSES` ( " +
                "`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`status`	TEXT NOT NULL)" ;
             String sql5 = "CREATE TABLE `PRODUCTS` ( " +
                "`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`name`	TEXT NOT NULL, " +
                "`price`	REAL NOT NULL, " +
                "`amount`	INT NOT NULL, " +
                "`isAvailable`	BOOLEAN NOT NULL, " +
                "`category_id`	INT NOT NULL )";
             String sql6 = "CREATE TABLE `PRODUCTS_IN_ORDERS` " +
                "(`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`order_id`	INT NOT NULL, " +
                "`product_id`	INT NOT NULL, " +
                "`amount`	INT NOT NULL, " +
                "FOREIGN KEY(`order_id`) REFERENCES `ORDERS`(`id`) ) ";
             String sql7 = "CREATE TABLE `USERS` " +
                "(`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`name`	TEXT NOT NULL, " +
                "`password` TEXT NOT NULL, " +
                "`user_type_id`	INT NOT NULL) " ;
             String sql8 = "CREATE TABLE `USER_TYPES` " +
                "(`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`type`	TEXT NOT NULL) ";
                
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.executeUpdate(sql4);
            stmt.executeUpdate(sql5);
            stmt.executeUpdate(sql6);
            stmt.executeUpdate(sql7);
            stmt.executeUpdate(sql8);
            String sql9 = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES ('Pencil', 3.50, 10, 'True', 1);"; 
            stmt.executeUpdate(sql9);
            String sql10 = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES ('Pen', 5, 15, 'True', 1);"; 
            stmt.executeUpdate(sql10);
            String sql11 = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES ('Notepad', 2, 20, 'True', 1);"; 
            stmt.executeUpdate(sql11);
            String sql12 = "INSERT INTO USERS (NAME,PASSWORD,user_type_id) " + "VALUES ('admin', 'admin', 1);"; 
            stmt.executeUpdate(sql12);
            String sql13 = "INSERT INTO USER_TYPES (TYPE) " + "VALUES ('admin');"; 
            stmt.executeUpdate(sql13);
            String sql14 = "INSERT INTO USER_TYPES (TYPE) " + "VALUES ('user');"; 
            stmt.executeUpdate(sql14);
            String sql15 = "INSERT INTO USERS (NAME,PASSWORD,user_type_id) " + "VALUES ('user1', 'password', 2);"; 
            stmt.executeUpdate(sql15);
            


//            addCategory("Office Supplies", "True", 1);
//            addCategory("Fruit", "True", 2);
//            addCategory("Games", "True", 3);
            int i1 = 1;
            float f1 = i1;
            addProduct("Apple", f1, 10, 2 );
            int i2 = 4;
            float f2 = i1;
            addProduct("Pineapple", f2, 5, 2);
            int i3 = 240;
            float f3 = i1;
            addProduct("Red Dead Redemption 2", f3, 5, 3);
            int i4 = 399;
            float f4 = i1;
            addProduct("Half-Life 3", f4, 3, 3);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

}