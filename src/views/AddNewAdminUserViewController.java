package views;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import models.AdminUsers;

/**
 * FXML Controller class
 *
 * @author gilemerson
 */
public class AddNewAdminUserViewController implements Initializable {
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField passwordReEnterTextField;
    @FXML private Label errMsgLabel;
   
    /**
     * 
     * @param event
     * @throws IOException 
     */
     public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "AdminUsersView.fxml", "Gils Hockey Shop - Admin Users");
    }
     
     /**
      * This method will check to see if the password is valid and matches all criteria, then it will
      * save the new password into the database and redirect the user to AdminUsersView.fxml 
      * @param event 
      */
    public void saveNewAdminUserButtonPushed(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException{
        
        if(validPasswords()){
            
        AdminUsers adminusers;
        
        adminusers = new AdminUsers(passwordTextField.getText());
        adminusers.insertIntoDB();
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "AdminUsersView.fxml", "Gils Hockey Shop - Admin Users"); 
        }     
    }
    
    /**
     * This method will validate that the passwords match and also make sure that the passwords are more then 5 characters in length
     * if not an error message will be displayed
     */
    public boolean validPasswords(){
        if(passwordTextField.getText().length() <5)
        {
            errMsgLabel.setText("Password must be longer than 5 characters");           
            return false;
        }
            
        if(passwordTextField.getText().equals(passwordReEnterTextField.getText()))
            return true;
        else
            errMsgLabel.setText("Password must match");
            return false;
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errMsgLabel.setText("");//set errMsgLabel text to be empty to start
    }    
}
