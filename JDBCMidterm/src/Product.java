import com.mysql.cj.Messages;
import com.mysql.cj.protocol.Message;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Product {
   private ResultSet rs;
    private Statement stm;
    private Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/TestDocker";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";

    public void tryconnect(){
        try
        {
             con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            System.out.println("Error" + e.getMessage());
        }
    }
    public void createTableProduct(){
        tryconnect();
        try{
        Statement statement = con.createStatement();
        String createtable = "CREATE TABLE Product(Id int, name varchar(255), price_per_unit float, active_forsale int)";
        Statement stmt = con.createStatement();
        stmt.execute(createtable);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addProduct(){
        tryconnect();
        try{
            String insertrecord = "INSERT INTO Product(Id,name,price_per_unit,active_forsale) VALUES(1,'Coke',2.00,1)," +
                    "(2,'Pepis',2.00,1)," +
                    "(3,'kizz',1.50,1)," +
                    "(4,'Redbull',2.00,0)";
            Statement stmt = con.createStatement();
            stmt. executeUpdate(insertrecord);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ShowProduct(){
        tryconnect();
            System.out.println("ID\tName\tPrice per Unit\tActive for Sell");
        try{  rs = stm.executeQuery("SELECT * FROM Product");}catch (SQLException e){
            System.out.println(e.getMessage());
        }
            try {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double pricePerUnit = rs.getFloat("price_per_unit");
                    boolean activeForSell = rs.getBoolean("active_for_sell");
                    System.out.printf("%d\t%s\t%.2f\t%b%n", id, name, pricePerUnit, activeForSell);
            }
            }catch(SQLException e){
                    System.out.println(e.getMessage());
            }


    }
}