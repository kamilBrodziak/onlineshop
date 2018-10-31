import java.sql.*;
import java.util.Map;
import java.util.Scanner;
import java.math.BigDecimal;
import java.io.File;
import java.lang.*;

public class DAO {


    public static void getCategories() { //Map<Integer, Category> categories
        
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery( "SELECT category_type_id, name FROM CATEGORIES;" );
            while ( rs.next() ) {
                int category_type_id = rs.getInt("category_type_id");
                String  name = rs.getString("name");
                System.out.println(category_type_id + " " + name);
               }


            stmt.close();
            c.close();
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }



    //     wczytuje wszystkie kategorie
    //     idCat = ....
    //     name = ....
    //     categories.put(idCat, new Category(idCat, name));
    //     ....

    //     wczytuje product
    //     idP = ...
    //     name = ...
    //     price - ...

    //     Product product = new Product(...)
    //     ...
    //     categories.get(idProdCateg).addToCategory(product);
    }

    
    public static void printProducts() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PRODUCTS;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int price  = rs.getInt("Price");
                String  amount = rs.getString("amount");
                //boolean isAvailable = rs.getBoolean("isAvailable");
                int category_id = rs.getInt("category_id");
                
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "PRICE = " + price );
                System.out.println( "AMOUNT = " + amount );
                //System.out.println( "isAvailable = " + isAvailable );
                System.out.println( "category_id =" + category_id);
                System.out.println();
               }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
    }



    public static void deleteProduct(int id) {
        Connection c = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "DELETE from PRODUCTS where ID=" + id + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
            } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            }
            System.out.println("Product deleted successfully");
    }


    public static void addProduct(String name, Float price, int amount, int category_id) {
        Connection c = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES ('" + name + "'," + price + "," + amount + ", 'True'," + category_id + ");" ;
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
            } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            }
            System.out.println("Product added successfully");
    }


    public static void addCategory(String name, String isAvailable, int category_type_id) {
        Connection c = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO CATEGORIES (NAME,isAvailable,category_type_id) " + "VALUES ('" + name + "'," + isAvailable + "," + category_type_id + ");" ;
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
            } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            }
            System.out.println("Category added successfully");
    }

    // to nie działa raczej 
    // public void insertProduct(String name, Bigdecimal price, int amount, String isAvailable, int category_id) {
    //     Class.forName("org.sqlite.JDBC");
    //     c = DriverManager.getConnection("jdbc:sqlite:test.db");
    //     System.out.println("Opened database successfully");
    //     stmt = c.createStatement();
                
    //     String sql = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES (" + name + "," + price + "," + amount + "," + isAvailable + "," + category_id + " );"; 
    //     stmt.executeUpdate(sql);
    // }
    

    public static void restoreDatabase() {
        File file = new File("test.db");
        if(file.delete()){
            System.out.println("test.db File deleted");
        } else System.out.println("File test.db doesn't exists");  
    
        Connection c = null;
        Statement stmt = null; 
      
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
             stmt = c.createStatement();
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
            addCategory("Office Supplies", "True", 1);
            addCategory("Fruit", "True", 2);
            addCategory("Games", "True", 3);
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
            stmt.close();
            c.close();
            } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            }
    }

}