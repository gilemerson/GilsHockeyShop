package views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.AdminUsers;

/**
 * FXML Controller class
 *
 * @author gilemerson
 */
public class AdminUsersViewController implements Initializable {

    @FXML private TableView<AdminUsers>AdminUserTable;
    @FXML private TableColumn<AdminUsers, Integer> adminIDColumn;
    @FXML private TableColumn<AdminUsers, String> passwordColumn; 
    
    /**
     * This method will change scenes and allow the user to add a new admin user
     * @param event
     * @throws IOException 
     */
    public void AddNewAdminUserButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "AddNewAdminUserView.fxml" , "Add New Admin User"); 
  }
    
    /**
     * This method will change scenes and allow the user to go back and view the store inventory 
     * @param event
     * @throws IOException 
     */
    public void ViewStoreButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "HockeyStoreTableView.fxml" , "Gils Hockey Store"); 
  }
    
    /**
     * This method will change scenes and allow the user to logout and return to the admin login window
     * @param event
     * @throws IOException 
     */
    public void LogoutButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "UserLoginView.fxml" , "Admin Login"); 
  }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //configure the table names
        adminIDColumn.setCellValueFactory(new PropertyValueFactory<AdminUsers, Integer>("userID"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<AdminUsers, String>("password"));
        
         try
        {
            AdministratorUsers();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());        
        }        
    } 
    
     /**
      * This method will load all the admin users from the database and load them into the TableView object
      * @throws SQLException 
      */
    public void AdministratorUsers() throws SQLException {
        ObservableList<AdminUsers> adminusers = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            //1.Connect to database
            conn = DriverManager.getConnection("jdbc:mysql://198.71.227.88:3306/demo_database", "EmersonGil", "Emerson0505");
            
            //2.create a statement object
            statement = conn.createStatement();
            
            //3.create the sql query
            resultSet = statement.executeQuery("SELECT * FROM adminusers");
            
            //4. create news store item object from each record
            while(resultSet.next())
            {
               AdminUsers newAdminUsers = new AdminUsers(resultSet.getString("password"));
                newAdminUsers.setUserID(resultSet.getInt("userID"));
                adminusers.add(newAdminUsers);
            }
            
            AdminUserTable.getItems().addAll(adminusers);
        }
        catch(Exception e)
        {
           System.err.println(e.getMessage());
        }
        finally
        {
            if(conn != null)
                conn.close();
            
            if(statement != null)
                statement.close();  
            
            if(resultSet != null)
                resultSet.close();
        }
    } 
}
   
