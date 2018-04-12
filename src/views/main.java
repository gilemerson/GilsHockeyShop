
package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gilemerson
 */
public class main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    
   /*
    * Launch Admin Login - UserLoginView.fxml
    */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UserLoginView.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
