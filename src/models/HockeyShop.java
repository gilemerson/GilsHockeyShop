package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gilemerson
 */
public class HockeyShop {
  
    private String equipment, size, warranty, price;
    private int itemID;

    public HockeyShop(String equipment, String size, String warranty, String price) {
        setEquipment(equipment);
        setSize(size);
        setWarranty(warranty);
        setPrice(price);
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public void insertIntoDB() throws SQLException{
    
        Connection conn = null;
        PreparedStatement preparedStatement = null; 
        
         try
        {
        //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://198.71.227.88:3306/demo_database", "EmersonGil", "Emerson0505");
            
            //2.create a string that holds the query with ? as users input
            String sql = "INSERT INTO hockeyitems (equipment, size, warranty, price)"
                    + "VALUES (?,?,?,?)";
            
            //3. Prepare the query 
            preparedStatement = conn.prepareStatement(sql);
            
             //5. Bind the values to the parameters
            preparedStatement.setString(1, equipment);
            preparedStatement.setString(2, size);
            preparedStatement.setString(3, warranty);
            preparedStatement.setString(4, price);         
            preparedStatement.executeUpdate();
            
    }
    catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
     finally
        {
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
        }     
      }
    }
    
   
