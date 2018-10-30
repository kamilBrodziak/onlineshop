import java.sql.*;
import java.util.Scanner;
import java.math.BigDecimal;
import java.io.File;
import java.lang.*;

public class DAO {
    
    
    public static void printProducts() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
                
                //System.out.println( "ID = " + id );
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


    // to nie działa raczej 
    // public void insertProduct(String name, Bigdecimal price, int amount, String isAvailable, int category_id) {
    //     Class.forName("org.sqlite.JDBC");
    //     c = DriverManager.getConnection("jdbc:sqlite:test.db");
    //     System.out.println("Opened database successfully");
    //     stmt = c.createStatement();
                
    //     String sql = "INSERT INTO PRODUCTS (NAME,PRICE,AMOUNT,isAvailable,category_id) " + "VALUES (" + name + "," + price + "," + amount + "," + isAvailable + "," + category_id + " );"; 
    //     stmt.executeUpdate(sql);
    // }
    
}