
package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import models.PasswordGenerator;

/**
 * FXML Controller class
 *
 * @author gilemerson
 */
public class UserLoginViewController implements Initializable {

    @FXML private TextField userIDTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label errMsgLabel;
    @FXML private ImageView imageView;
    private File imageFile;
    
    /**
     * If Passwords match correctly - Store Encrypted password in DB - Then Login user and open AdminUsersView.fxml 
     * @param event
     * @throws IOException 
     */
    public void LoginButtonPushed(ActionEvent event) throws IOException, SQLException{
      
        //Query the database with the userID provided, get the salt and encrypt password stored in the database
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        int userNumber = Integer.parseInt(userIDTextField.getText());
        
        try{
            
            // 1.Connect to DB
            conn = DriverManager.getConnection("jdbc:mysql://198.71.227.88:3306/demo_database", "EmersonGil", "Emerson0505");
            
            // 2.Create a query string with ? used instead of the value given by the user
            String sql = "SELECT password, salt FROM adminusers WHERE userID = ?";
            
            //3. Prepare the statement 
            ps = conn.prepareStatement(sql);
            
            //4. Bind the userID to the ?
            ps.setInt(1, userNumber);
            
            //5. Execute the query
            resultSet = ps.executeQuery();
            
            //6. extract the password and salt from the resultSet
            String dbPassword = null;
            byte[] salt = null;
            
            while(resultSet.next())
            {
                dbPassword = resultSet.getString("password");
                
                Blob blob = resultSet.getBlob("salt");
                
                //convert into a byte array       
                int blobLength = (int) blob.length();
                salt = blob.getBytes(1, blobLength);            
                
            }
            
            //convert password given by the user into an encrypted password using the salt from the database
            String userID = PasswordGenerator.getSHA512Password(passwordField.getText(), salt);
            
            SceneChanger sc = new SceneChanger();
            
            //if the password match, change to the adminuserview
            if(userID.equals(dbPassword))
            sc.changeScenes(event, "AdminUsersView.fxml", "Gils Hockey Shop - Admin Users");
            
            else
                //if they do not match, update the error message
                errMsgLabel.setText("The userID and password do correspond");
                                  
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }      
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        errMsgLabel.setText("");//Set errMsgLabel text to be empty to start
        
        //load the image for the login page sidebar
        try 
        {
            imageFile = new File("./src/images/hockeyshop.png");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }    
    
}
