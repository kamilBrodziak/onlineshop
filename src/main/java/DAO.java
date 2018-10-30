import java.sql.*;
import java.math.BigDecimal;
import java.io.File;
import java.lang.*;

public class DAO {
    
    
    public void createDatabase() {
        File file = new File("onlineshop/test.db");
        if(file.delete()){
            System.out.println("onlineshop/test.db File deleted");
        } else System.out.println("File onlineshop/test.db doesn't exists");  
    
        Connection c = null;
        Statement stmt = null; 
      
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:onlineshop/test.db");
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
    }
    
    
    public void printProducts() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:onlineshop/test.db");
            System.out.println("Opened database successfully");
            
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
                System.out.println( " category_id =" + category_id);
                System.out.println();
               }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
        }
    }


    //może nie działać jeszcze
    public void insertProduct(String name, Bigdecimal price, int amount, String isAvailable, int category_id) {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:onlineshop/test.db");
        System.out.println("Opened database successfully");
        stmt = c.createStatement();
                
        String sql = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES (" + name + "," + price + "," + amount + "," + isAvailable + "," + category_id + " );"; 
        stmt.executeUpdate(sql);
    }
    
}