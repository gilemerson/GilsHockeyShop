 
package models;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gilemerson
 */
public class AdminUsers {
  
   private String password;
   private int userID;
   private byte[] salt;
    
    public AdminUsers(String Password) throws NoSuchAlgorithmException {
        this.password = Password;
        salt = PasswordGenerator.getSalt();
        this.password = PasswordGenerator.getSHA512Password(password, salt);
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }
    
   
   /**
    * This method will INSERT the password and salt in the adminusers database and save the salted password 
    * @throws SQLException 
    */
   public void insertIntoDB() throws SQLException{
    
        Connection conn = null;
        PreparedStatement preparedStatement = null; 
        
         try
        {
        //1. Connect to the database
             conn = DriverManager.getConnection("jdbc:mysql://198.71.227.88:3306/demo_database", "EmersonGil", "Emerson0505");
            
            //2.create a string that holds the query with ? as users input
            String sql = "INSERT INTO adminusers (password, salt)"
                    + "VALUES (?,?)";
            
            //3. Prepare the query 
            preparedStatement = conn.prepareStatement(sql);
            
             //5. Bind the values to the parameters
            preparedStatement.setString(1, password);  
            preparedStatement.setBlob(2, new javax.sql.rowset.serial.SerialBlob(salt));  
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

