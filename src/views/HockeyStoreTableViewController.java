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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HockeyShop;

/**
 * FXML Controller class
 *
 * @author gilemerson
 */
public class HockeyStoreTableViewController implements Initializable {
    
    @FXML private TableView<HockeyShop>GilsHockeyShopTable;
    @FXML private TableColumn<HockeyShop, Integer> itemIDColumn;
    @FXML private TableColumn<HockeyShop, String> equipmentColumn;
    @FXML private TableColumn<HockeyShop, String> sizeColumn;
    @FXML private TableColumn<HockeyShop, String> warrantyColumn;
    @FXML private TableColumn<HockeyShop, String> priceColumn;
    
    @FXML private Button editShopItemsButton;
    
    /**
     * if a item in the shop has been selected, enable the edit button
     */
    public void ShopItemSelected(){
        editShopItemsButton.setDisable(false);
    }
    
    /**
    * This method will change scenes and allow the user to see the admin users with accounts
    */
    public void ViewAdminUsersButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "AdminUsersView.fxml" , "Gils Hockey Shop - Admin Users"); 
  }
    
   /**
    * This method will allow the user to switch to the TableView to add a new store item
    */
    public void newHockeyShopItemButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "AddNewStoreItemView.fxml" , "Admin User - Add New Store Item"); 
  }
    /**
     * This method will allow the user to logout and return to the admin login window
     */
    public void LogoutButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "UserLoginView.fxml" , "Admin Login"); 
  }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //disable edit button until admin selected item from the table
        this.editShopItemsButton.setDisable(true);
        
        //configure the table names
        itemIDColumn.setCellValueFactory(new PropertyValueFactory<HockeyShop, Integer>("itemID"));
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<HockeyShop, String>("equipment"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<HockeyShop, String>("size"));
        warrantyColumn.setCellValueFactory(new PropertyValueFactory<HockeyShop, String>("warranty"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<HockeyShop, String>("price"));
        
        try
        {
            loadHockeyShopItems();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());        
        }        
    }
    
    /**
     * This method will load all the store items from the database and load them into the TableView object
     * @throws java.sql.SQLException
     */
    public void loadHockeyShopItems() throws SQLException {
        ObservableList<HockeyShop> hockeyshop = FXCollections.observableArrayList();
        
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
            resultSet = statement.executeQuery("SELECT * FROM hockeyitems");
            
            //4. create news store item object from each record
            while(resultSet.next())
            {
               HockeyShop newHockeyShop = new HockeyShop(resultSet.getString("equipment"),
                                                       resultSet.getString("size"),
                                                       resultSet.getString("warranty"),
                                                       resultSet.getString("price"));
                newHockeyShop.setItemID(resultSet.getInt("ItemID"));
                hockeyshop.add(newHockeyShop);
            }
            
            GilsHockeyShopTable.getItems().addAll(hockeyshop);
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


