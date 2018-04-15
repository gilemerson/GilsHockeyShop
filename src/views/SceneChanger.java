
package views;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.HockeyShop;

/**
 *
 * @author gilemerson
 */
public class SceneChanger {
     public void changeScenes(ActionEvent event, String viewName, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        
        //get parent stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        
    }
     
    /**
     * This method will change scenes and pre-load the next scene with a user object
     * @param event
     * @param viewName
     * @param title
     * @param hockeyshop
     * @param controllerClass
     * @throws IOException 
     */
     public void chageScenes(ActionEvent event, String viewName, String title, HockeyShop hockeyshop, ControllerClass controllerClass) throws IOException{
         
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource(viewName));
         Parent parent = loader.load();
         
         Scene scene = new Scene(parent);
         
         //access the controller class and preload the user data
         controllerClass = loader.getController();
         controllerClass.preloadData(hockeyshop);

        //get parent stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        
     }
}
