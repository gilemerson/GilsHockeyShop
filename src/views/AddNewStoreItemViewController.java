package views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.HockeyShop;

/**
 * FXML Controller class
 *
 * @author gilemerson
 */
public class AddNewStoreItemViewController implements Initializable, ControllerClass {

    @FXML private TextField equipmentTextField;
    @FXML private TextField sizeTextField;
    @FXML private TextField warrantyTextField;
    @FXML private TextField priceTextField;
    @FXML private Label headerLabel;
    
    private HockeyShop hockeyshop;
    
   /**
    * This method will change back to the TabeView of StoreItems without adding a new item. All the data in the form will be lost
    */
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        //Change scenes after cancel button has been pushed
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "HockeyStoreTableView.fxml", "Store Items");
    }
    
   /**
    * When the saveNewStoreItemButtonPushed is pushed - the new store item will be saved into the database and the admin user will be
    * redirected to HockeyStoreTableView.fxml
    * @param event
    * @throws SQLException
    * @throws IOException 
    */
    public void saveNewStoreItemButtonPushed(ActionEvent event) throws SQLException, IOException{
        
        if(hockeyshop != null) //we need to edit an existing store item
        {
            updateHockeyShopItem();
            hockeyshop.updateHockeyShopItemInDB(); 
        }
        else //we need to create a new hockeyshop item
        {
        hockeyshop = new HockeyShop(equipmentTextField.getText(), sizeTextField.getText(), warrantyTextField.getText(), priceTextField.getText());
        hockeyshop.insertIntoDB();
        }
        //Change scenes after save button has been pushed
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "HockeyStoreTableView.fxml", "Store Items");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    }        

    @Override
    public void preloadData(HockeyShop hockeyshop) {
        this.hockeyshop = hockeyshop;
        this.equipmentTextField.setText(hockeyshop.getEquipment());
        this.priceTextField.setText(hockeyshop.getPrice());
        this.sizeTextField.setText(hockeyshop.getSize());
        this.warrantyTextField.setText(hockeyshop.getWarranty());
        
        //Change header text on AddNewStoreItemView.fxml to 'Edit Store Items' when the users clicks on the edit item button
        this.headerLabel.setText("Edit Store Items");
    }
 
    /**
     * This method will read from the GUI field and update the hockey shop items objects
     */
    public void updateHockeyShopItem(){
        hockeyshop.setEquipment(equipmentTextField.getText());
        hockeyshop.setPrice(priceTextField.getText());
        hockeyshop.setSize(sizeTextField.getText());
        hockeyshop.setWarranty(warrantyTextField.getText());

    }
}
