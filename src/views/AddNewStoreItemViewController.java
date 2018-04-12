package views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import models.HockeyShop;

/**
 * FXML Controller class
 *
 * @author gilemerson
 */
public class AddNewStoreItemViewController implements Initializable {

    @FXML private TextField equipmentTextField;
    @FXML private TextField sizeTextField;
    @FXML private TextField warrantyTextField;
    @FXML private TextField priceTextField;
    
   /**
    * This method will change back to the TabeView of StoreItems without adding a new item. All the data in the form will be lost
    */
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "HockeyStoreTableView.fxml", "Store Items");
    }
    
    
   /**
    * When the saveNewStoreItemButtonPushed is pushed - the new store item will be saved into the database and the admin user will be
    * redirected to HockeyStoreTableView.fxml
    */
    public void saveNewStoreItemButtonPushed(ActionEvent event) throws SQLException, IOException{
        
        HockeyShop hockeyshop;
        
        hockeyshop = new HockeyShop(equipmentTextField.getText(), sizeTextField.getText(), warrantyTextField.getText(), priceTextField.getText());
        hockeyshop.insertIntoDB();
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "HockeyStoreTableView.fxml", "Store Items");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }        
}
